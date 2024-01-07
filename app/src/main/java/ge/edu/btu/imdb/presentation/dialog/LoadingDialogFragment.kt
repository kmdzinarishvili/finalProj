package ge.edu.btu.imdb.presentation.dialog

import ge.edu.btu.imdb.R

class LoadingDialogFragment : BaseDialogFragment() {

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_loading_dialog
    }

    fun dismissDialog() {
        if (isVisible) {
            dismiss()
        }
    }
}