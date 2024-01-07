package ge.edu.btu.imdb.presentation.fragment

import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.WorkManager
import androidx.work.OneTimeWorkRequest
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
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val csvWorkRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java)
                .setConstraints(constraints)
                .build()

            context?.let { WorkManager.getInstance(it).enqueue(csvWorkRequest) }
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

