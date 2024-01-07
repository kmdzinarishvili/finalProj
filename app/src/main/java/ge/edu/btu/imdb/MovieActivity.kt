package ge.edu.btu.imdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import ge.edu.btu.imdb.navigation.NavControllerManager
import org.koin.android.ext.android.inject

class MovieActivity : AppCompatActivity() {

    private val navControllerManager by inject<NavControllerManager>()

    private val navController: NavController by lazy {
        findNavController(R.id.mainFragmentContainerView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navControllerManager.setNavController(navController)
    }
}