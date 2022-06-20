package developer.abdulaziz.my_codial_app.Fragments.Guruhlar

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.my_codial_app.Classes.Group
import developer.abdulaziz.my_codial_app.Database.MyDbHelper
import developer.abdulaziz.my_codial_app.Object.MyObject
import developer.abdulaziz.my_codial_app.databinding.FragmentGroupAddBinding

class GroupAddFragment : Fragment() {
    private lateinit var binding: FragmentGroupAddBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroupAddBinding.inflate(layoutInflater)
        binding.apply {
            val myDbHelper = MyDbHelper(root.context)
            val listMentorName = ArrayList<String>()
            val listMentor = myDbHelper.readMentor()
            for (i in listMentor) {
                if (i.myKurs == MyObject.positionKurs) listMentorName.add(i.name.toString())
            }
            spinnerMentor.adapter =
                ArrayAdapter(
                    root.context,
                    android.R.layout.simple_expandable_list_item_1,
                    listMentorName
                )
            val listTime = ArrayList<String>()
            listTime.add("12:00 - 14:00")
            listTime.add("14:00 - 16:00")
            listTime.add("16:00 - 18:00")
            listTime.add("18:00 - 20:00")
            spinnerTime.adapter =
                ArrayAdapter(root.context, android.R.layout.simple_expandable_list_item_1, listTime)

            val listDate = ArrayList<String>()
            listDate.add("Toq kunlar")
            listDate.add("Juft kunlar")
            spinnerDate.adapter =
                ArrayAdapter(root.context, android.R.layout.simple_expandable_list_item_1, listDate)

            back.setOnClickListener { findNavController().popBackStack() }
            save.setOnClickListener {
                val name = groupName.text.toString()
                val mentor = listMentor[spinnerMentor.selectedItemPosition]
                val time = listTime[spinnerTime.selectedItemPosition]
                val date = listDate[spinnerDate.selectedItemPosition]

                if (name.isNotEmpty() && time.isNotEmpty() && date.isNotEmpty()) {
                    val group = Group(
                        name,
                        mentor,
                        time,
                        date,
                        0
                    )
                    myDbHelper.createGroup(group)
                    findNavController().popBackStack()
                } else Toast.makeText(root.context, "Empty", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}