package ge.edu.btu.imdb.splash.presentation.fragment

import ge.edu.btu.imdb.corepresentation.fragment.CoreBaseFragment
import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.splash.presentation.viewmodel.SplashViewModel
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