package developer.abdulaziz.my_codial_app.Fragments.Mentorlar

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.my_codial_app.Adapters.MyMentorAdapter
import developer.abdulaziz.my_codial_app.Classes.Mentor
import developer.abdulaziz.my_codial_app.Database.MyDbHelper
import developer.abdulaziz.my_codial_app.Object.MyObject
import developer.abdulaziz.my_codial_app.R
import developer.abdulaziz.my_codial_app.databinding.FragmentMentorShowBinding
import developer.abdulaziz.my_codial_app.databinding.ItemDeleteBinding
import developer.abdulaziz.my_codial_app.databinding.ItemMentorEditDialogBinding

class MentorShowFragment : Fragment() {
    private lateinit var binding: FragmentMentorShowBinding
    private lateinit var myMentorAdapter: MyMentorAdapter
    private lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentorShowBinding.inflate(layoutInflater)
        binding.apply {
            myDbHelper = MyDbHelper(root.context)
            val listMentor = ArrayList<Mentor>()
            for (i in myDbHelper.readMentor()) {
                if (i.myKurs == MyObject.positionKurs) listMentor.add(i)
            }
            title.text = "${MyObject.kurslar.name} Development"
            addMentor.setOnClickListener { findNavController().navigate(R.id.mentorAddFragment) }
            back.setOnClickListener { findNavController().popBackStack() }
            myMentorAdapter =
                MyMentorAdapter(listMentor, object : MyMentorAdapter.OnMenuClickListener {
                    override fun onEdit(mentor: Mentor, position: Int) {
                        val alertDialog = AlertDialog.Builder(root.context).create()
                        val item = ItemMentorEditDialogBinding.inflate(layoutInflater).apply {
                            mentorSurname.setText(mentor.surname)
                            mentorName.setText(mentor.name)
                            mentorLastname.setText(mentor.lastname)
                            close.setOnClickListener { alertDialog.cancel() }
                            save.setOnClickListener {
                                val surname = mentorSurname.text.toString()
                                val name = mentorName.text.toString()
                                val lastname = mentorLastname.text.toString()
                                if (surname.isNotEmpty() && name.isNotEmpty() && lastname.isNotEmpty()) {
                                    mentor.surname = surname
                                    mentor.name = name
                                    mentor.lastname = lastname
                                    myDbHelper.updateMentor(mentor)
                                    listMentor[position] = mentor
                                    myMentorAdapter.notifyItemChanged(position)
                                    alertDialog.cancel()
                                } else Toast.makeText(root.context, "Empty", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                        alertDialog.setView(item.root)
                        alertDialog.show()
                    }

                    override fun onDelete(mentor: Mentor, position: Int) {
                        val alertDialog = AlertDialog.Builder(root.context).create()
                        val item = ItemDeleteBinding.inflate(layoutInflater).apply {
                            save.setOnClickListener {
                                myDbHelper.deleteMentor(mentor)
                                listMentor.remove(mentor)
                                myMentorAdapter.notifyItemRemoved(position)
                                alertDialog.cancel()
                            }
                            close.setOnClickListener { alertDialog.cancel() }
                        }
                        alertDialog.setView(item.root)
                        alertDialog.show()
                    }
                })
            rvMentorlar.adapter = myMentorAdapter
            return root
        }
    }
}