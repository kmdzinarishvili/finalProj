package ge.edu.btu.imdb.coredatabase.di

import android.app.Application
import androidx.room.Room
import ge.edu.btu.imdb.coredatabase.data.database.FavoriteMoviesDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

const val FAVORITE_MOVIES_DATABASE_NAME = "favorite_movies_database"

private fun provideAppDatabase(application: Application): FavoriteMoviesDatabase {
    return Room.databaseBuilder(
        application,
        FavoriteMoviesDatabase::class.java,
        FAVORITE_MOVIES_DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()
}

private fun provideUserDao(database: FavoriteMoviesDatabase) = database.favoriteMoviesDao()

val databaseModule = module {
    single { provideAppDatabase(androidApplication()) }
    single { provideUserDao(database = get()) }
}