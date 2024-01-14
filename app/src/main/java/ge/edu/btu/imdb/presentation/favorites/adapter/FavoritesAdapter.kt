package ge.edu.btu.imdb.presentation.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ge.edu.btu.imdb.domain.model.MoviesDomainModel
import ge.edu.btu.imdb.common.extension.setImage
import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.databinding.MovieItemLayoutBinding
import ge.edu.btu.imdb.common.constants.Constants
import ge.edu.btu.imdb.presentation.home.adapter.DiffCallback


class FavoritesAdapter :
    ListAdapter<MoviesDomainModel.ResultDomain, FavoritesAdapter.MovieViewHolder>(DiffCallback()) {

    private var onFavoriteClicked: ((MoviesDomainModel.ResultDomain) -> Unit)? = null
    private var onItemClickListener: ((MoviesDomainModel.ResultDomain) -> Unit)? = null

    fun setOnFavoriteClickListener(listener: (MoviesDomainModel.ResultDomain) -> Unit) {
        onFavoriteClicked = listener
    }

    fun setOnItemClickListener(listener: (MoviesDomainModel.ResultDomain) -> Unit) {
        onItemClickListener = listener
    }

    class MovieViewHolder(private val binding: MovieItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: MoviesDomainModel.ResultDomain,
            onItemClickListener: ((MoviesDomainModel.ResultDomain) -> Unit)?,
            onFavoriteClicked: ((MoviesDomainModel.ResultDomain) -> Unit)?
        ) {
            with(binding) {
                dashboardHeadLineTextView.text = item.title
                dashboardDateTextView.text = item.releaseDate?.dropLast(6)
                dashboardMovieImageView.setImage(Constants.IMG_BASE_URL + item.posterPath)
                dashboardCategoryTextView.text = item.genreIds?.first()
                dashboardFavoritesImageView.setImageResource(R.drawable.ic_favorites_true)
                dashboardFavoritesImageView.setOnClickListener {
                    onFavoriteClicked?.invoke(item)
                }
                root.setOnClickListener {
                    onItemClickListener?.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            MovieItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onItemClickListener, onFavoriteClicked)
    }
}