package ge.edu.btu.imdb.download

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import ge.edu.btu.imdb.data.model.remote.MoviesDomainModel
import ge.edu.btu.imdb.data.repository.FavoritesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.IOException


class DownloadWorker(val context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
), KoinComponent
{

    private val favoritesRepository: FavoritesRepository by inject()

    override fun doWork(): Result {
        val workerScope = CoroutineScope(Dispatchers.Default)
        var result = Result.success();

        workerScope.launch {
            val moviesList =   favoritesRepository.getAllFavoriteMovies().firstOrNull()
            if (moviesList == null){
                result = Result.failure()
            }else{
                val csvData = convertToCSV(moviesList)
                saveCSVToFile(csvData)
            }
        }
        return result
    }

    private fun convertToCSV(mList: List<MoviesDomainModel.ResultDomain>): String {
        val header = "Id,Title,Rating,ReleaseDate\n"
        val csvData = StringBuilder(header)
        for (movie in mList) {
            val line = "${movie.id},${movie.title},${movie.voteAverage},${movie.releaseDate}\n"
            csvData.append(line)
        }
        return csvData.toString()
    }

    private fun saveCSVToFile(csvData: String){

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, Companion.FILE_NAME)
            put(MediaStore.MediaColumns.MIME_TYPE, Companion.FILE_TYPE)
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS + Companion.DIRECTORY_NAME)
        }

        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY), contentValues)
        uri?.let { documentUri ->
            try {
                resolver.openOutputStream(documentUri)?.use { outputStream ->
                    outputStream.write(csvData.toByteArray())
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("DownloadWorker", "Error saving CSV file: ${e.message}")
            }
        }

    }
    companion object {
        const val FILE_NAME = "favorites.csv"
        const val FILE_TYPE = "text/csv"
        const val DIRECTORY_NAME = "/IMDB"
    }
}