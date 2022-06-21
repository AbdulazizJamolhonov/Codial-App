package developer.abdulaziz.my_codial_app.Adapters

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter
import developer.abdulaziz.my_codial_app.Fragments.Guruhlar.*

class GroupPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment =
        if (position == 0) GroupPagerFragment()
        else GroupPagerFragment2()
}