package ge.edu.btu.imdb

import android.app.Application
import ge.edu.btu.imdb.dashboardimpl.container.di.dashboardMapperModule
import ge.edu.btu.imdb.dashboardimpl.container.di.dashboardRepositoryModule
import ge.edu.btu.imdb.dashboardimpl.container.di.dashboardUseCaseModule
import ge.edu.btu.imdb.dashboardimpl.container.di.serviceModule
import ge.edu.btu.imdb.coredatabase.di.coreDBMapperModule
import ge.edu.btu.imdb.coredatabase.di.coreDBRepositoryModule
import ge.edu.btu.imdb.coredatabase.di.coreDBUseCaseModule
import ge.edu.btu.imdb.coredatabase.di.databaseModule
import ge.edu.btu.imdb.di.navigation.navigationModule
import ge.edu.btu.imdb.di.viewmodel.viewModelModule
import ge.edu.btu.imdb.corenetwork.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApp)
            modules(
                navigationModule, viewModelModule, networkModule, serviceModule,
                dashboardMapperModule, dashboardRepositoryModule, dashboardUseCaseModule,
                databaseModule, coreDBRepositoryModule, coreDBMapperModule,
                coreDBUseCaseModule
            )
        }
    }
}