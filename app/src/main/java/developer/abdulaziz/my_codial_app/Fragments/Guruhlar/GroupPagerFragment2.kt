package developer.abdulaziz.my_codial_app.Fragments.Guruhlar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import developer.abdulaziz.my_codial_app.Adapters.MyGroupAdapter
import developer.abdulaziz.my_codial_app.Classes.Group
import developer.abdulaziz.my_codial_app.Database.MyDbHelper
import developer.abdulaziz.my_codial_app.databinding.FragmentGroupPager2Binding

class GroupPagerFragment2 : Fragment() {
    private lateinit var binding: FragmentGroupPager2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroupPager2Binding.inflate(layoutInflater)
        binding.apply {
            val myDbHelper = MyDbHelper(root.context)
            val listGroup = ArrayList<Group>()
            for (i in myDbHelper.readGroup()) {
                if (i.openClose == 1) listGroup.add(i)
            }
            rvGuruhlarShow.adapter = MyGroupAdapter(
                listGroup,
                myDbHelper.readTalaba(),
                object : MyGroupAdapter.OnMenuClickListener {
                    override fun onShow(group: Group, position: Int) {

                    }

                    override fun onEdit(group: Group, position: Int) {

                    }

                    override fun onDelete(group: Group, position: Int) {

                    }
                })
            return root
        }
    }
}