package ge.edu.btu.imdb.presentation.splash.vm

import androidx.lifecycle.ViewModel
import ge.edu.btu.imdb.common.extension.viewModelScope
import ge.edu.btu.imdb.navigation.dashboard.DashboardNavigationApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class SplashViewModel(private val navigationApi: DashboardNavigationApi) : ViewModel() {

    fun navigateToDashboard() {
        viewModelScope(Dispatchers.Main) {
            delay(DELAY_DURATION)
            navigationApi.navigateToDashboard()
        }
    }

    companion object {
        const val DELAY_DURATION = 2000L
    }
}