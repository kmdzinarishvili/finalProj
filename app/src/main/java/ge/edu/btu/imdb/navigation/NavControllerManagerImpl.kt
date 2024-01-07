package ge.edu.btu.imdb.navigation

import androidx.navigation.NavController
import ge.edu.btu.imdb.navigation.NavControllerManager

class NavControllerManagerImpl(): NavControllerManager {
    private lateinit var navController: NavController

    override fun setNavController(navController: NavController) {
       this.navController = navController
    }

    override fun getNavController(): NavController {
        return navController
    }
}