package ge.edu.btu.imdb.presentation.details.ui

import ge.edu.btu.imdb.common.extension.lifecycleScope
import ge.edu.btu.imdb.domain.model.MoviesDomainModel
import ge.edu.btu.imdb.common.constants.MovieItemKeyConstant.MOVIE_ITEM_KEY
import ge.edu.btu.imdb.common.extension.setImage
import ge.edu.btu.imdb.common.extension.viewBinding
import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.databinding.FragmentDetailBinding
import ge.edu.btu.imdb.presentation.details.vm.DetailViewModel
import ge.edu.btu.imdb.common.constants.Constants.IMG_BASE_URL
import ge.edu.btu.imdb.presentation.base.CoreBaseFragment
import kotlin.reflect.KClass

class DetailFragment : CoreBaseFragment<DetailViewModel>() {
    private val binding by viewBinding(FragmentDetailBinding::bind)
    override val viewModelClass: KClass<DetailViewModel>
        get() = DetailViewModel::class

    override val layout: Int
        get() = R.layout.fragment_detail

    override fun onBind() {
        val movieItem =
            requireArguments().getParcelable<MoviesDomainModel.ResultDomain>(MOVIE_ITEM_KEY)!!
        setupViews(movieItem)
        setupHeartImageView(movieItem)
        setListener()
    }

    private fun setupViews(movieItem: MoviesDomainModel.ResultDomain) {
        with(binding) {
            titleTextView.text = movieItem.title
            posterImageView.setImage(IMG_BASE_URL + movieItem.posterPath)
            genreTextView.text = movieItem.genreIds?.first()
            dateTextView.text = movieItem.releaseDate?.dropLast(6)
            descriptionTextView.text = movieItem.overview
            ratingTextView.text = movieItem.voteAverage.toString()
        }
    }

    private fun setupHeartImageView(movieItem: MoviesDomainModel.ResultDomain) {
        val heartImageView = binding.heartImageView

        fun updateFavoriteStatus(isFavorite: Boolean) {
            val drawableRes =
                if (isFavorite) R.drawable.ic_favorites_true else R.drawable.ic_favorites_false
            heartImageView.setBackgroundResource(drawableRes)
        }

        lifecycleScope {
            val isFavorite = viewModel.isMovieInFavorites(movieItem.id)
            updateFavoriteStatus(isFavorite)

            heartImageView.setOnClickListener {
                lifecycleScope {
                    viewModel.manageFavoriteMovie(movieItem)
                    updateFavoriteStatus(!isFavorite)
                }
            }
        }
    }

    private fun setListener() {
        binding.backImageButton.setOnClickListener {
            viewModel.navigateToDashboard()
        }
    }
}