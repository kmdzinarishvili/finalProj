package ge.edu.btu.imdb.navigation.dashboard

import ge.edu.btu.imdb.navigation.NavControllerManager
import ge.edu.btu.imdb.R

class DashboardNavigationImpl (private val navControllerManager: NavControllerManager) :
    DashboardNavigationApi {
    override fun navigateToDashboard() {
        navControllerManager.getNavController().navigate(R.id.dashboard_nav_graph)
    }
}