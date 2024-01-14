package ge.edu.btu.imdb.presentation.container.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ge.edu.btu.imdb.presentation.favorites.ui.FavoritesFragment
import ge.edu.btu.imdb.presentation.home.ui.HomeFragment

class ContainerPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> FavoritesFragment()
            else -> throw IllegalArgumentException()
        }
    }
}