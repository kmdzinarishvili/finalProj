package ge.edu.btu.imdb.dashboardimpl.feature.favorites.presentation.fragment

import ge.edu.btu.imdb.corecommon.extensions.lifecycleScope
import ge.edu.btu.imdb.coredomain.remote.MoviesDomainModel
import ge.edu.btu.imdb.corepresentation.fragment.CoreBaseFragment
import ge.edu.btu.imdb.corepresentation.extension.hide
import ge.edu.btu.imdb.corepresentation.extension.show
import ge.edu.btu.imdb.corepresentation.extension.viewBinding
import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.dashboardimpl.container.feature.favorites.presentation.adapter.FavoritesAdapter
import ge.edu.btu.imdb.databinding.FragmentFavoritesBinding
import ge.edu.btu.imdb.dashboardimpl.feature.favorites.presentation.viewmodel.FavoritesViewModel
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

