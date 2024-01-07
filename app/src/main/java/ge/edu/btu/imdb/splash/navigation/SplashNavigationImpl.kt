package ge.edu.btu.imdb.splash.navigation

import ge.edu.btu.imdb.corecommon.navigation.NavControllerManager
import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.splashapi.SplashNavigationApi

class SplashNavigationImpl(private val navControllerManager: NavControllerManager) :
    SplashNavigationApi {
    override fun navigateToSplash() {
        navControllerManager.getNavController().navigate(R.id.splash_nav_graph)
    }
}