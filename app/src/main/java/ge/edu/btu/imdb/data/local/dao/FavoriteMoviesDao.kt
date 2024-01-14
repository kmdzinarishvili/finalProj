package ge.edu.btu.imdb.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ge.edu.btu.imdb.data.model.local.FavoriteMoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: FavoriteMoviesEntity)

    @Query("SELECT * FROM favorite_movies")
    fun getAllFavoriteMovies(): Flow<List<FavoriteMoviesEntity>>

    @Query("DELETE FROM favorite_movies WHERE id = :id")
    suspend fun deleteMovieById(id: Int): Int

    @Query("SELECT EXISTS(SELECT * FROM favorite_movies WHERE id=:id)")
    suspend fun isMovieFavorite(id: Int): Boolean

    @Query("SELECT id FROM favorite_movies")
    fun getAllFavoriteMovieIds(): Flow<List<Int>>
}