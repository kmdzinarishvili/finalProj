package ge.edu.btu.imdb.presentation.custom_view

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.widget.addTextChangedListener
import ge.edu.btu.imdb.common.extension.hide
import ge.edu.btu.imdb.common.extension.show
import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.databinding.SearchLayoutBinding


class SearchCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    interface OnTextChangeListener {
        fun onTextChange(query: String)
    }

    private val binding by lazy {
        SearchLayoutBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    private fun invisibleCategories() {
        with(binding) {
            topRatedChip.hide()
            popularChip.hide()
            filterImageView.setImageResource(R.drawable.ic_filter_false)
        }
    }

    private fun visibleCategories() {
        with(binding) {
            topRatedChip.show()
            popularChip.show()
            popularChip.isChecked = true
            filterImageView.setImageResource(R.drawable.ic_filter_true)
        }
    }

    fun setTyping() {
        with(binding) {
            filterImageView.hide()
            cancelTextView.show()
        }
        invisibleCategories()
    }

    fun cancelClicked() {
        with(binding) {
            cancelTextView.hide()
            filterImageView.show()
            filterImageView.setImageResource(R.drawable.ic_filter_false)
            searchEditText.setText("")
            searchEditText.clearFocus()
        }
    }

    private var filterButtonFalse = false

    fun setFilterView() {
        with(binding) {
            filterImageView.setOnClickListener {
                filterButtonFalse = !filterButtonFalse

                if (filterButtonFalse) {
                    visibleCategories()
                } else {
                    invisibleCategories()
                }
            }
            popularChip.isChecked = true
        }
    }

    fun setOnFilterClickListener(listener: OnClickListener) {
        binding.filterImageView.setOnClickListener(listener)
    }

    fun setOnCancelClickListener(listener: OnClickListener) {
        binding.cancelTextView.setOnClickListener(listener)
    }

    fun setOnSearchClickListener(listener: OnClickListener) {
        binding.searchEditText.setOnClickListener(listener)
    }

    fun setOnPopularClickListener(listener: OnClickListener) {
        binding.popularChip.setOnClickListener(listener)
    }

    fun setOnTopRatedClickListener(listener: OnClickListener) {
        binding.topRatedChip.setOnClickListener(listener)
    }

    fun addTextChangeListener(textChangeListener: OnTextChangeListener) {
        val handler = Handler(Looper.getMainLooper())
        val delayMills = 1000L
        binding.searchEditText.addTextChangedListener {
            var searchQuery = ""
            handler.removeCallbacksAndMessages(null)
            val query = it?.toString() ?: ""
            searchQuery = query
            if (query.length >= 2) {
                handler.postDelayed({
                    if (searchQuery == query) {
                        textChangeListener.onTextChange(query)
                    }
                }, delayMills)
            }
        }
    }
}
