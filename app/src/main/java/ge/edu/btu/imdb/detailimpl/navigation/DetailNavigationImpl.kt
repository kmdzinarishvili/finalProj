package ge.edu.btu.imdb.detailimpl.navigation

import android.os.Bundle
import ge.edu.btu.imdb.corecommon.navigation.NavControllerManager
import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.detailapi.DetailNavigationApi

class DetailNavigationImpl (private val navControllerManager: NavControllerManager) :
    DetailNavigationApi {

    override fun navigateToDetail(bundle: Bundle) {
        navControllerManager.getNavController().navigate(R.id.detail_nav_graph,bundle)
    }
}
