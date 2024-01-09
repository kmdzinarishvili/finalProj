package ge.edu.btu.imdb.download

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class FileWriteCompleteReceiver(private val listener: FileWriteCompleteListener) : BroadcastReceiver() {

    interface FileWriteCompleteListener {
        fun onFileWriteComplete()
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == ACTION_FILE_WRITE_COMPLETE) {
            listener.onFileWriteComplete()
        }
    }

    companion object {
        const val ACTION_FILE_WRITE_COMPLETE = "ge.edu.btu.imdb.download.FILE_WRITE_COMPLETE"

        fun sendFileWriteCompleteBroadcast(context: Context) {
            val intent = Intent(ACTION_FILE_WRITE_COMPLETE)
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
        }
    }
}
