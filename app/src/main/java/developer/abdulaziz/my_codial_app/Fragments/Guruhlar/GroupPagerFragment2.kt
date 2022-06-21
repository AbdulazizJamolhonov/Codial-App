package developer.abdulaziz.my_codial_app.Fragments.Guruhlar

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.my_codial_app.Adapters.MyGroupAdapter
import developer.abdulaziz.my_codial_app.Classes.Group
import developer.abdulaziz.my_codial_app.Database.MyDbHelper
import developer.abdulaziz.my_codial_app.Object.MyObject
import developer.abdulaziz.my_codial_app.R
import developer.abdulaziz.my_codial_app.databinding.FragmentGroupPager2Binding
import developer.abdulaziz.my_codial_app.databinding.ItemDeleteBinding
import developer.abdulaziz.my_codial_app.databinding.ItemGroupEditDialogBinding

class GroupPagerFragment2 : Fragment() {
    private lateinit var binding: FragmentGroupPager2Binding
    private lateinit var myGroupAdapter: MyGroupAdapter
    private lateinit var listMentor: ArrayList<String>
    private lateinit var listGroup: ArrayList<Group>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroupPager2Binding.inflate(layoutInflater)
        binding.apply {
            val myDbHelper = MyDbHelper(root.context)

            val mentorList = myDbHelper.readMentor()
            listGroup = ArrayList()
            for (i in myDbHelper.readGroup()) {
                if (i.openClose == 1) listGroup.add(i)
            }

            listMentor = ArrayList()
            for (e in mentorList) {
                if (e.myKurs == MyObject.positionKurs) listMentor.add("${e.name}")
            }

            myGroupAdapter = MyGroupAdapter(
                listGroup,
                myDbHelper.readTalaba(),
                object : MyGroupAdapter.OnMenuClickListener {
                    override fun onShow(group: Group, position: Int) {
                        MyObject.group = group
                        MyObject.positionGroup = position
                        findNavController().navigate(R.id.groupDarsFragment)
                    }

                    override fun onEdit(group: Group, position: Int) {
                        val alertDialog = AlertDialog.Builder(root.context).create()
                        val item = ItemGroupEditDialogBinding.inflate(layoutInflater).apply {
                            groupName.setText(group.name)
                            spinnerMentor.adapter = ArrayAdapter(
                                root.context,
                                android.R.layout.simple_expandable_list_item_1,
                                listMentor
                            )
                            spinnerTime.adapter = ArrayAdapter(
                                root.context,
                                android.R.layout.simple_expandable_list_item_1,
                                MyObject.listTime
                            )
                            save.setOnClickListener {
                                val name = groupName.text.toString()
                                val mentor = mentorList[spinnerMentor.selectedItemPosition]
                                val time = MyObject.listTime[spinnerTime.selectedItemPosition]
                                if (name.isNotEmpty() && time.isNotEmpty()) {
                                    group.name = name
                                    group.time = time
                                    group.mentor = mentor
                                    myDbHelper.updateGroup(group)
                                    listGroup[position] = group
                                    alertDialog.cancel()
                                    myGroupAdapter.notifyItemChanged(position)
                                }
                            }
                            close.setOnClickListener { alertDialog.cancel() }
                        }
                        alertDialog.setView(item.root)
                        alertDialog.show()
                    }

                    override fun onDelete(group: Group, position: Int) {
                        val alertDialog = AlertDialog.Builder(root.context).create()
                        val item = ItemDeleteBinding.inflate(layoutInflater).apply {
                            save.setOnClickListener {
                                myDbHelper.deleteGroup(group)
                                listGroup.remove(group)
                                myGroupAdapter.notifyItemRemoved(position)
                                alertDialog.cancel()
                            }
                            close.setOnClickListener { alertDialog.cancel() }
                        }
                        alertDialog.setView(item.root)
                        alertDialog.show()
                    }
                })
            return root
        }
    }
}