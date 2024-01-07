package ge.edu.btu.imdb.presentation.fragment

import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.presentation.viewmodel.SplashViewModel
import kotlin.reflect.KClass

class SplashFragment : CoreBaseFragment<SplashViewModel>() {

    override val viewModelClass: KClass<SplashViewModel>
        get() = SplashViewModel::class

    override val layout: Int
        get() = R.layout.fragment_splash

    override fun onBind() {
        viewModel.navigateToDashboard()
    }
}