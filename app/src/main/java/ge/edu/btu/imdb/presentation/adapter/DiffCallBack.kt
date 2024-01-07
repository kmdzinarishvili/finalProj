package ge.edu.btu.imdb.presentation.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

/**
 * DiffUtil.ItemCallback implementation for comparing items in a RecyclerView adapter.
 *
 * @param T The type of items being compared.
 */
class DiffCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    /**
     * Checks if the items have the same identity.
     *
     * @param oldItem The old item to compare.
     * @param newItem The new item to compare.
     * @return True if the items have the same identity, false otherwise.
     */
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem === newItem
    }

    /**
     * Checks if the contents of the items are the same.
     *
     * @param oldItem The old item to compare.
     * @param newItem The new item to compare.
     * @return True if the contents of the items are the same, false otherwise.
     */
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}