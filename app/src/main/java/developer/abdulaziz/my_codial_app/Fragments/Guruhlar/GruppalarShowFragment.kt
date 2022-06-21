package developer.abdulaziz.my_codial_app.Fragments.Guruhlar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import developer.abdulaziz.my_codial_app.Adapters.GroupPagerAdapter
import developer.abdulaziz.my_codial_app.Object.MyObject
import developer.abdulaziz.my_codial_app.R
import developer.abdulaziz.my_codial_app.databinding.FragmentGruppalarShowBinding

class GruppalarShowFragment : Fragment() {
    private lateinit var binding: FragmentGruppalarShowBinding
    private lateinit var groupPagerAdapter: GroupPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGruppalarShowBinding.inflate(layoutInflater)
        binding.apply {
            title.text = "${MyObject.kurslar.name} Development"
            addGroup.setOnClickListener { findNavController().navigate(R.id.groupAddFragment) }
            back.setOnClickListener { findNavController().popBackStack() }

            groupPagerAdapter = GroupPagerAdapter(this@GruppalarShowFragment)
            viewPager2.adapter = groupPagerAdapter
            val listTab = arrayOf("Ochilayotgan guruhlar", "Ochilgan guruhlar")
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.text = listTab[position]
            }.attach()
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab?.position == 0) {
                        addGroup.visibility = View.VISIBLE
                        addGroup.setOnClickListener { findNavController().navigate(R.id.groupAddFragment) }
                    } else {
                        addGroup.visibility = View.INVISIBLE
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })

            return root
        }
    }
}