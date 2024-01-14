package ge.edu.btu.imdb.presentation

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.navigation.NavControllerManager
import ge.edu.btu.imdb.presentation.favorites.receiver.DownloadCompleteReceiver
import org.koin.android.ext.android.inject

class MovieActivity : AppCompatActivity(), DownloadCompleteReceiver.DownloadCompleteListener  {

    private val navControllerManager by inject<NavControllerManager>()

    private val navController: NavController by lazy {
        findNavController(R.id.mainFragmentContainerView)
    }

    private val downloadCompleteReceiver = DownloadCompleteReceiver(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navControllerManager.setNavController(navController)

        LocalBroadcastManager.getInstance(this).registerReceiver(
            downloadCompleteReceiver,
            IntentFilter(DownloadCompleteReceiver.ACTION_FILE_WRITE_COMPLETE)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(downloadCompleteReceiver)

    }

    override fun onDownloadComplete() {
        Toast.makeText(this, "Download Successfully Completed", Toast.LENGTH_SHORT).show()
    }

}