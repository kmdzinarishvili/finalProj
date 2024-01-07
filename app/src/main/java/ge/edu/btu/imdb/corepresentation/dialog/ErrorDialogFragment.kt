package ge.edu.btu.imdb.corepresentation.dialog

import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.databinding.FragmentErrorDialogBinding
import ge.edu.btu.imdb.corepresentation.extension.viewBinding

class ErrorDialogFragment : BaseDialogFragment() {

    private val binding by viewBinding(FragmentErrorDialogBinding::bind)
    private var onRefreshClickListener: (() -> Unit)? = null

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_error_dialog
    }

    override fun onStart() {
        super.onStart()
        binding.refreshImageView.setOnClickListener {
            onRefreshClickListener?.invoke()
        }
    }

    fun setOnRefreshClickListener(onRefreshClickListener: (() -> Unit)) {
        this.onRefreshClickListener = onRefreshClickListener
    }
}