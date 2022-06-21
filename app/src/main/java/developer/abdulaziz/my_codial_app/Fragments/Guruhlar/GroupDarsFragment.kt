package developer.abdulaziz.my_codial_app.Fragments.Guruhlar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.my_codial_app.Database.MyDbHelper
import developer.abdulaziz.my_codial_app.Object.MyObject
import developer.abdulaziz.my_codial_app.R
import developer.abdulaziz.my_codial_app.databinding.FragmentGroupDarsBinding

class GroupDarsFragment : Fragment() {
    private lateinit var binding: FragmentGroupDarsBinding
    private lateinit var myDbHelper: MyDbHelper

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroupDarsBinding.inflate(layoutInflater)
        binding.apply {
            myDbHelper = MyDbHelper(root.context)
            val group = MyObject.group
            title.text = group.name
            textDars.text =
                "${group.name}\nO'quvchilar soni: ${myDbHelper.readTalaba().size} ta\nVaqti: ${group.time}"
            startDars.setOnClickListener {
                group.openClose = 1
                myDbHelper.updateGroup(group)
                it.visibility = View.GONE
            }
            add.setOnClickListener { findNavController().navigate(R.id.groupDarsFragment) }
            back.setOnClickListener { findNavController().popBackStack() }

            return root
        }
    }
}