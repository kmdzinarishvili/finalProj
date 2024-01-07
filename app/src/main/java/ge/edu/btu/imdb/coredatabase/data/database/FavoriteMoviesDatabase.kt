package ge.edu.btu.imdb.coredatabase.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ge.edu.btu.imdb.coredatabase.data.dao.FavoriteMoviesDao
import ge.edu.btu.imdb.coredatabase.data.database.FavoriteMoviesDatabase.Companion.DB_VERSION
import ge.edu.btu.imdb.coredatabase.data.model.FavoriteMoviesEntity
import ge.edu.btu.imdb.coredatabase.data.typeconverter.Converters


@Database(
    entities = [FavoriteMoviesEntity::class], version = DB_VERSION, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FavoriteMoviesDatabase : RoomDatabase() {
    abstract fun favoriteMoviesDao(): FavoriteMoviesDao

    companion object {
        const val DB_VERSION = 6
    }
}