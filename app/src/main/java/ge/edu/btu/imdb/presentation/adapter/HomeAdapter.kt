package ge.edu.btu.imdb.presentation.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import ge.edu.btu.imdb.data.model.remote.MoviesDomainModel
import ge.edu.btu.imdb.extension.setImage
import ge.edu.btu.imdb.extension.viewBinding
import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.databinding.MovieItemLayoutBinding
import ge.edu.btu.imdb.constants.Constants.IMG_BASE_URL

class HomeAdapter :
    PagingDataAdapter<MoviesDomainModel.ResultDomain, HomeAdapter.MovieViewHolder>(DiffCallback()) {

    private var onFavoriteClicked: ((MoviesDomainModel.ResultDomain) -> Unit)? = null
    private var onItemClickListener: ((MoviesDomainModel.ResultDomain) -> Unit)? = null
    private var favoriteIds: List<Int> = emptyList()

    fun setOnFavoriteClickListener(listener: (MoviesDomainModel.ResultDomain) -> Unit) {
        onFavoriteClicked = listener
    }

    fun setOnItemClickListener(listener: (MoviesDomainModel.ResultDomain) -> Unit) {
        onItemClickListener = listener
    }

    fun setFavoriteIds(ids: List<Int>) {
        favoriteIds = ids
        notifyDataSetChanged()
    }

    class MovieViewHolder(
        private val binding: MovieItemLayoutBinding,
        private val onFavoriteClicked: ((MoviesDomainModel.ResultDomain) -> Unit)?,
        private val onItemClickListener: ((MoviesDomainModel.ResultDomain) -> Unit)?,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MoviesDomainModel.ResultDomain, isFavorite: Boolean) {
            with(binding) {
                dashboardHeadLineTextView.text = item.title
                dashboardDateTextView.text = item.releaseDate?.dropLast(6)
                dashboardMovieImageView.setImage(IMG_BASE_URL + item.posterPath)
                dashboardCategoryTextView.text = item.genreIds?.first()
                updateFavoriteIcon(isFavorite)
                dashboardFavoritesImageView.setOnClickListener {
                    onFavoriteClicked?.invoke(item)
                }
                root.setOnClickListener {
                    onItemClickListener?.invoke(item)
                }
            }
        }

        private fun updateFavoriteIcon(isFavorite: Boolean) {
            val drawableRes =
                if (isFavorite) R.drawable.ic_favorites_true else R.drawable.ic_favorites_false
            binding.dashboardFavoritesImageView.setImageResource(drawableRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            parent.viewBinding(MovieItemLayoutBinding::inflate),
            onFavoriteClicked,
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { movie ->
            val isFavorite = movie.id in favoriteIds
            holder.bind(movie, isFavorite)
        }
    }
}