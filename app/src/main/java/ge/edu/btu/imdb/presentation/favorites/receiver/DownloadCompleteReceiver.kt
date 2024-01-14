package ge.edu.btu.imdb.presentation.favorites.receiver

import android.content.Context
import android.content.BroadcastReceiver
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class DownloadCompleteReceiver(private val listener: DownloadCompleteListener) : BroadcastReceiver() {

    interface DownloadCompleteListener {
        fun onDownloadComplete()
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == ACTION_FILE_WRITE_COMPLETE) {
            listener.onDownloadComplete()
        }
    }

    companion object {
        const val ACTION_FILE_WRITE_COMPLETE = "ge.edu.btu.imdb.download.FILE_WRITE_COMPLETE"

        fun sendDownloadCompleteBroadcast(context: Context) {
            val intent = Intent(ACTION_FILE_WRITE_COMPLETE)
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
        }
    }
}