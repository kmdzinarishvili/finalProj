package ge.edu.btu.imdb.common.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import ge.edu.btu.imdb.R

fun ImageView.setImage(url: String?){
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_no_data)
        .error(R.drawable.ic_no_data)
        .into(this)
}