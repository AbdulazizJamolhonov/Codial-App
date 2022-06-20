package developer.abdulaziz.my_codial_app.Adapters

import androidx.fragment.app.*
import developer.abdulaziz.my_codial_app.Fragments.Guruhlar.*

class GroupPagerAdapter(private val list: ArrayList<String>, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int = list.size
    override fun getItem(position: Int): Fragment =
        if (position == 0) GroupPagerFragment()
        else GroupPagerFragment2()

    override fun getPageTitle(position: Int): CharSequence = list[position]
}