package ge.edu.btu.imdb.navigation.splash

import ge.edu.btu.imdb.navigation.NavControllerManager
import ge.edu.btu.imdb.R

class SplashNavigationImpl(private val navControllerManager: NavControllerManager) :
    SplashNavigationApi {
    override fun navigateToSplash() {
        navControllerManager.getNavController().navigate(R.id.splash_nav_graph)
    }
}