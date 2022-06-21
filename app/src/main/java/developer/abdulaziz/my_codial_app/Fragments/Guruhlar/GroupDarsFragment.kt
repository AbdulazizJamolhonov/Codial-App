package developer.abdulaziz.my_codial_app.Fragments.Guruhlar

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.my_codial_app.Adapters.MyTalabaAdapter
import developer.abdulaziz.my_codial_app.Classes.Talaba
import developer.abdulaziz.my_codial_app.Database.MyDbHelper
import developer.abdulaziz.my_codial_app.Object.MyObject
import developer.abdulaziz.my_codial_app.R
import developer.abdulaziz.my_codial_app.databinding.FragmentGroupDarsBinding
import developer.abdulaziz.my_codial_app.databinding.ItemDeleteBinding

@SuppressLint("SetTextI18n")
class GroupDarsFragment : Fragment() {
    private lateinit var binding: FragmentGroupDarsBinding
    private lateinit var myTalabaAdapter: MyTalabaAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroupDarsBinding.inflate(layoutInflater)
        binding.apply {
            val myDbHelper = MyDbHelper(root.context)
            val listTalaba = ArrayList<Talaba>()
            for (i in myDbHelper.readTalaba()) {
                if (i.myGuruh == MyObject.positionKurs) listTalaba.add(i)
            }
            val group = MyObject.group

            title.text = group.name
            textDars.text =
                "${group.name}\nO'quvchilar soni: ${myDbHelper.readTalaba().size} ta\nVaqti: ${group.time}"

            if (MyObject.openClose == 0) {
                startDars.visibility = View.VISIBLE
                startDars.setOnClickListener {
                    group.openClose = 1
                    myDbHelper.updateGroup(group)
                    startDars.isClickable = false
                    MyObject.openClose = 1
                    textStart.textSize = 20F
                    textStart.text = "Guruhga dars boshlandi"
                }
            } else startDars.visibility = View.GONE

            startDars.setOnClickListener {
                group.openClose = 1
                myDbHelper.updateGroup(group)
                startDars.isClickable = false
                MyObject.openClose = 1
                textStart.textSize = 20F
                textStart.text = "Guruhga dars boshlandi"
            }
            add.setOnClickListener { findNavController().navigate(R.id.groupTalabaAddFragment) }
            back.setOnClickListener { findNavController().popBackStack() }

            myTalabaAdapter =
                MyTalabaAdapter(listTalaba, object : MyTalabaAdapter.OnMenuClickListener {
                    override fun onEdit(talaba: Talaba, position: Int) {
                        MyObject.talaba = talaba
                        MyObject.psitionTalaba = position
                        findNavController().navigate(R.id.groupTalabaEditFragment)
                    }

                    override fun onDelete(talaba: Talaba, position: Int) {
                        val alertDialog = AlertDialog.Builder(root.context).create()
                        val item = ItemDeleteBinding.inflate(layoutInflater).apply {
                            save.setOnClickListener {
                                myDbHelper.deleteTalaba(talaba)
                                listTalaba.remove(talaba)
                                myTalabaAdapter.notifyItemRemoved(position)
                                alertDialog.cancel()
                            }
                            close.setOnClickListener { alertDialog.cancel() }
                        }
                        alertDialog.setView(item.root)
                        alertDialog.show()
                    }
                })

            rvDars.adapter = myTalabaAdapter

            return root
        }
    }
}