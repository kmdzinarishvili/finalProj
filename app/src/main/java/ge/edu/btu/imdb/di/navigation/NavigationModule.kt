package ge.edu.btu.imdb.di.navigation

import ge.edu.btu.imdb.navigation.NavControllerManager
import ge.edu.btu.imdb.navigation.dashboard.DashboardNavigationImpl
import ge.edu.btu.imdb.navigation.dashboard.DashboardNavigationApi
import ge.edu.btu.imdb.navigation.detail.DetailNavigationApi
import ge.edu.btu.imdb.navigation.detail.DetailNavigationImpl
import ge.edu.btu.imdb.navigation.NavControllerManagerImpl
import ge.edu.btu.imdb.navigation.splash.SplashNavigationImpl
import ge.edu.btu.imdb.navigation.splash.SplashNavigationApi
import org.koin.dsl.module

val navigationModule = module {
    single<NavControllerManager> { NavControllerManagerImpl() }
    single<SplashNavigationApi> { SplashNavigationImpl(get()) }
    single<DetailNavigationApi> { DetailNavigationImpl(get()) }
    single<DashboardNavigationApi> { DashboardNavigationImpl(navControllerManager = get()) }
}


