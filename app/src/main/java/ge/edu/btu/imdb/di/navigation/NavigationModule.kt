package ge.edu.btu.imdb.di.navigation

import ge.edu.btu.imdb.corecommon.navigation.NavControllerManager
import ge.edu.btu.imdb.dashboardimpl.navigation.DashboardNavigationImpl
import ge.edu.btu.imdb.dashboardapi.DashboardNavigationApi
import ge.edu.btu.imdb.detailimpl.navigation.DetailNavigationImpl
import ge.edu.btu.imdb.detailapi.DetailNavigationApi
import ge.edu.btu.imdb.navigation.NavControllerManagerImpl
import ge.edu.btu.imdb.splash.navigation.SplashNavigationImpl
import ge.edu.btu.imdb.splashapi.SplashNavigationApi
import org.koin.dsl.module

val navigationModule = module {
    single<NavControllerManager> { NavControllerManagerImpl() }
    single<SplashNavigationApi> { SplashNavigationImpl(get()) }
    single<DetailNavigationApi> { DetailNavigationImpl(get()) }
    single<DashboardNavigationApi> { DashboardNavigationImpl(navControllerManager = get()) }
}


