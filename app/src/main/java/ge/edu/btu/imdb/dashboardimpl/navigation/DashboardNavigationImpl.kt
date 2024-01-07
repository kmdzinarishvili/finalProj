package ge.edu.btu.imdb.dashboardimpl.navigation

import ge.edu.btu.imdb.corecommon.navigation.NavControllerManager
import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.dashboardapi.DashboardNavigationApi

class DashboardNavigationImpl (private val navControllerManager: NavControllerManager) :
    DashboardNavigationApi {
    override fun navigateToDashboard() {
        navControllerManager.getNavController().navigate(R.id.dashboard_nav_graph)
    }
}