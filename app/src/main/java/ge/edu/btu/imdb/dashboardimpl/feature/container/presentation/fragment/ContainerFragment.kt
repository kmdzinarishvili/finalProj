package ge.edu.btu.imdb.dashboardimpl.feature.container.presentation.fragment

import androidx.viewpager2.widget.ViewPager2
import ge.edu.btu.imdb.corepresentation.fragment.CoreBaseFragment
import ge.edu.btu.imdb.corepresentation.extension.viewBinding
import ge.edu.btu.imdb.R
import ge.edu.btu.imdb.dashboardimpl.container.feature.container.presentation.adapter.ContainerPagerAdapter
import ge.edu.btu.imdb.dashboardimpl.container.feature.container.presentation.viewmodel.ContainerViewModel
import ge.edu.btu.imdb.databinding.FragmentContainerBinding
import kotlin.reflect.KClass

class ContainerFragment : CoreBaseFragment<ContainerViewModel>() {

    private val binding by viewBinding(FragmentContainerBinding::bind)

    override val viewModelClass: KClass<ContainerViewModel>
        get() = ContainerViewModel::class

    override val layout: Int
        get() = R.layout.fragment_container

    override fun onBind() {
        viewPagerSetUp()
    }

    private fun viewPagerSetUp() {
        with(binding) {
            val viewPager: ViewPager2 = viewPager
            viewPager.adapter = ContainerPagerAdapter(requireActivity())

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 -> bottomCustomView.setHomeBackgrounds()
                        1 -> bottomCustomView.setFavoritesBackgrounds()
                    }
                }
            })

            bottomCustomView.setOnHomeClickListener {
                viewPager.setCurrentItem(0, true)
            }
            bottomCustomView.setOnFavoritesClickListener {
                viewPager.setCurrentItem(1, true)
            }
        }
    }
}

