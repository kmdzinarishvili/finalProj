package ge.edu.btu.imdb.corepresentation.dialog

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