package ge.edu.btu.imdb

import android.app.DownloadManager
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import ge.edu.btu.imdb.download.DownloadBroadcastReceiver
import ge.edu.btu.imdb.download.FileWriteCompleteReceiver
import ge.edu.btu.imdb.navigation.NavControllerManager
import org.koin.android.ext.android.inject

class MovieActivity : AppCompatActivity(), FileWriteCompleteReceiver.FileWriteCompleteListener  {

    private val navControllerManager by inject<NavControllerManager>()

    private val navController: NavController by lazy {
        findNavController(R.id.mainFragmentContainerView)
    }

    private var downloadReceiver: DownloadBroadcastReceiver? = null
    private val fileWriteCompleteReceiver = FileWriteCompleteReceiver(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navControllerManager.setNavController(navController)

        downloadReceiver = DownloadBroadcastReceiver()
        registerReceiver(downloadReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        LocalBroadcastManager.getInstance(this).registerReceiver(
            fileWriteCompleteReceiver,
            IntentFilter(FileWriteCompleteReceiver.ACTION_FILE_WRITE_COMPLETE)
        )
    }
    override fun onDestroy() {
        super.onDestroy()

        // Unregister the BroadcastReceiver to avoid memory leaks
        downloadReceiver?.let {
            unregisterReceiver(it)
        }
        LocalBroadcastManager.getInstance(this).unregisterReceiver(fileWriteCompleteReceiver)

    }

    override fun onFileWriteComplete() {
        Toast.makeText(this, "Download Successfully Completed", Toast.LENGTH_SHORT).show()
    }


}