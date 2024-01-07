package ge.edu.btu.imdb.corecommon.navigation

import androidx.navigation.NavController

interface NavControllerManager {
    fun setNavController(navController: NavController)
    fun getNavController():NavController
}

