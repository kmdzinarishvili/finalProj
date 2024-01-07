package ge.edu.btu.imdb

import android.app.Application
import ge.edu.btu.imdb.di.dashboard.dashboardMapperModule
import ge.edu.btu.imdb.di.dashboard.dashboardRepositoryModule
import ge.edu.btu.imdb.di.dashboard.dashboardUseCaseModule
import ge.edu.btu.imdb.di.service.serviceModule
import ge.edu.btu.imdb.di.db.coreDBMapperModule
import ge.edu.btu.imdb.di.db.coreDBRepositoryModule
import ge.edu.btu.imdb.di.db.coreDBUseCaseModule
import ge.edu.btu.imdb.di.db.databaseModule
import ge.edu.btu.imdb.di.navigation.navigationModule
import ge.edu.btu.imdb.di.viewmodel.viewModelModule
import ge.edu.btu.imdb.di.network.networkModule
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