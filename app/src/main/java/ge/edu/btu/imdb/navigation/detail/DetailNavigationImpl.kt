package ge.edu.btu.imdb.navigation.detail

import android.os.Bundle
import ge.edu.btu.imdb.navigation.NavControllerManager
import ge.edu.btu.imdb.R

class DetailNavigationImpl (private val navControllerManager: NavControllerManager) :
    DetailNavigationApi {

    override fun navigateToDetail(bundle: Bundle) {
        navControllerManager.getNavController().navigate(R.id.detail_nav_graph,bundle)
    }
}
