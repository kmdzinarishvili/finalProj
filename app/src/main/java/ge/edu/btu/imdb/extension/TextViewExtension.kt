package ge.edu.btu.imdb.extension

import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun TextView.setTextColorCompat(@ColorRes colorResource: Int) {
    val colorValue = ContextCompat.getColor(context, colorResource)
    setTextColor(colorValue)
}