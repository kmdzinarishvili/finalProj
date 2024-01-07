package ge.edu.btu.imdb.splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import ge.edu.btu.imdb.corecommon.extensions.viewModelScope
import ge.edu.btu.imdb.dashboardapi.DashboardNavigationApi
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