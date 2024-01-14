package ge.edu.btu.imdb.presentation.favorites.worker

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import ge.edu.btu.imdb.domain.model.MoviesDomainModel
import ge.edu.btu.imdb.domain.repository.local.FavoritesRepository
import ge.edu.btu.imdb.presentation.favorites.receiver.DownloadCompleteReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class DownloadWorker(val context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
), KoinComponent
{

    private val favoritesRepository: FavoritesRepository by inject()

    override fun doWork(): Result {

        val workerScope = CoroutineScope(Dispatchers.Default)
        var result = Result.success()

        workerScope.launch {
            val moviesList =   favoritesRepository.getAllFavoriteMovies().firstOrNull()
            if (moviesList == null){
                Log.e("DownloadWorker", "Error: Favorite Movies Empty.")
                result = Result.failure()
            }else{
                val csvData = convertToCSV(moviesList)
                saveCSVToFile(csvData)
            }
        }
        return result
    }

    private fun convertToCSV(mList: List<MoviesDomainModel.ResultDomain>): String {
        val header = "Id,Title,Rating,Release Date\n"
        val csvData = StringBuilder(header)
        for (movie in mList) {
            val line = "${movie.id},${movie.title},${movie.voteAverage},${movie.releaseDate}\n"
            csvData.append(line)
        }
        return csvData.toString()
    }

    private fun saveCSVToFile(csvData: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, FILE_NAME)
                put(MediaStore.MediaColumns.MIME_TYPE, FILE_TYPE)
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS + DIRECTORY_NAME)
            }
            val resolver = context.contentResolver
            val uri = resolver.insert(MediaStore.Files.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY), contentValues)
            uri?.let { documentUri ->
                try {
                    resolver.openOutputStream(documentUri)?.use { outputStream ->
                        outputStream.write(csvData.toByteArray())
                    }
                    DownloadCompleteReceiver.sendDownloadCompleteBroadcast(context)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Log.e("DownloadWorker", "Error saving CSV file: ${e.message}")
                }
            }

        } else {
            val externalDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
            if (externalDir != null ) {
                val file = File(externalDir, FILE_NAME)
                val outputStream = FileOutputStream(file)
                outputStream.write(csvData.toByteArray())
                outputStream.close()
                DownloadCompleteReceiver.sendDownloadCompleteBroadcast(context)
            } else {
                Log.e("DownloadWorker", "Error: External directory not available.")
            }
        }
    }
    companion object {
        const val FILE_NAME = "favorites.csv"
        const val FILE_TYPE = "text/csv"
        const val DIRECTORY_NAME = "/IMDB"
    }
}