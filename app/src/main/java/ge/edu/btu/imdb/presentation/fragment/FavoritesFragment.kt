package ge.edu.btu.imdb.presentation.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import ge.edu.btu.imdb.extension.lifecycleScope
import ge.edu.btu.imdb.data.model.remote.MoviesDomainModel
import ge.edu.btu.imdb.extension.hide
import ge.edu.btu.imdb.extension.show
import ge.edu.btu.imdb.extension.viewBinding
import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.presentation.adapter.FavoritesAdapter
import ge.edu.btu.imdb.databinding.FragmentFavoritesBinding
import ge.edu.btu.imdb.download.DownloadWorker
import ge.edu.btu.imdb.presentation.viewmodel.FavoritesViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.reflect.KClass


class FavoritesFragment : CoreBaseFragment<FavoritesViewModel>() {

    private val binding by viewBinding(FragmentFavoritesBinding::bind)

    private var writePermissionsGranted = false
    private lateinit var permissionsLauncher: ActivityResultLauncher<String>


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
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
            } else {
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

