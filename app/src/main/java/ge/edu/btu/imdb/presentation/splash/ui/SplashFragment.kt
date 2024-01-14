package ge.edu.btu.imdb.presentation.splash.ui

import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.presentation.base.CoreBaseFragment
import ge.edu.btu.imdb.presentation.splash.vm.SplashViewModel
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