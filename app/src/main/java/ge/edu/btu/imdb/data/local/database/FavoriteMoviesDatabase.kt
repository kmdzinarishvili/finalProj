package ge.edu.btu.imdb.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ge.edu.btu.imdb.data.local.dao.FavoriteMoviesDao
import ge.edu.btu.imdb.data.local.database.FavoriteMoviesDatabase.Companion.DB_VERSION
import ge.edu.btu.imdb.data.model.local.FavoriteMoviesEntity
import ge.edu.btu.imdb.data.local.typeconverter.Converters


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