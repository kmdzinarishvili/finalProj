package ge.edu.btu.imdb.presentation.favorites.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import ge.edu.btu.imdb.common.extension.lifecycleScope
import ge.edu.btu.imdb.domain.model.MoviesDomainModel
import ge.edu.btu.imdb.common.extension.hide
import ge.edu.btu.imdb.common.extension.show
import ge.edu.btu.imdb.common.extension.viewBinding
import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.presentation.favorites.adapter.FavoritesAdapter
import ge.edu.btu.imdb.databinding.FragmentFavoritesBinding
import ge.edu.btu.imdb.presentation.base.CoreBaseFragment
import ge.edu.btu.imdb.presentation.favorites.vm.FavoritesViewModel
import ge.edu.btu.imdb.presentation.favorites.worker.DownloadWorker
import kotlinx.coroutines.flow.collectLatest
import kotlin.reflect.KClass


class FavoritesFragment : CoreBaseFragment<FavoritesViewModel>() {

    private val binding by viewBinding(FragmentFavoritesBinding::bind)
    override val viewModelClass: KClass<FavoritesViewModel>
        get() = FavoritesViewModel::class

    override val layout: Int
        get() = R.layout.fragment_favorites

    private val favoritesAdapter by lazy {
        FavoritesAdapter()
    }

    override fun onBind() {
        observeFavoriteMovies()
        setUpRecycler()
        setOnCLickListeners()
    }

    private fun setUpRecycler() {
        binding.favoritesRecyclerView.adapter = favoritesAdapter
    }

    private fun observeFavoriteMovies() {
        lifecycleScope {
            viewModel.favoriteMovies
                .collectLatest { favoriteMovies ->
                    handleFavoriteMoviesList(favoriteMovies)
                }
        }
    }

    private fun setOnCLickListeners() {
        favoritesAdapter.setOnFavoriteClickListener { data ->
            viewModel.deleteFavoriteMovie(data)
        }
        favoritesAdapter.setOnItemClickListener { item ->
            viewModel.navigateToDetails(item)
        }
        binding.downloadImageView.setOnClickListener {
            if (checkPermissions()) {
                val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()

                val csvWorkRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java)
                    .setConstraints(constraints)
                    .build()

                context?.let { WorkManager.getInstance(it).enqueue(csvWorkRequest) }
            }
        }
    }

    private fun checkPermissions(): Boolean{
        val sdkAfter29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
        if (sdkAfter29){
            return true
        }
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
            return false
        }
    }

    private fun handleFavoriteMoviesList(favoriteMovies: List<MoviesDomainModel.ResultDomain>) {
        if (favoriteMovies.isEmpty()) {
            showEmptyState()
        } else {
            showNonEmptyState()
            favoritesAdapter.submitList(favoriteMovies)
        }
    }

    private fun showEmptyState() {
        with(binding) {
            favoritesRecyclerView.hide()
            emptyFavoritesTextView.show()
            emptyFavoritesImageView.show()
        }
    }

    private fun showNonEmptyState() {
        with(binding) {
            favoritesRecyclerView.show()
            emptyFavoritesTextView.hide()
            emptyFavoritesImageView.hide()
        }
    }
}

