package ge.edu.btu.imdb.presentation.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.databinding.BottomCustomViewBinding

class BottomNavigationCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding by lazy {
        BottomCustomViewBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    fun setOnHomeClickListener(listener: OnClickListener) {
        binding.homeImageView.setOnClickListener(listener)
    }

    fun setOnFavoritesClickListener(listener: OnClickListener) {
        binding.favoritesImageView.setOnClickListener(listener)
    }

     fun setHomeBackgrounds() {
        with(binding) {
            homeImageView.setImageResource(R.drawable.ic_home_active)
            favoritesImageView.setImageResource(R.drawable.ic_favorites_inactive)
        }
    }

     fun setFavoritesBackgrounds() {
        with(binding) {
            homeImageView.setImageResource(R.drawable.ic_home_inactive)
            favoritesImageView.setImageResource(R.drawable.ic_favorites_active)
        }
    }
}

