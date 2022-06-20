package developer.abdulaziz.my_codial_app.Fragments.Mentorlar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.my_codial_app.Classes.Mentor
import developer.abdulaziz.my_codial_app.Database.MyDbHelper
import developer.abdulaziz.my_codial_app.Object.MyObject
import developer.abdulaziz.my_codial_app.databinding.FragmentMentorAddBinding

class MentorAddFragment : Fragment() {
    private lateinit var binding: FragmentMentorAddBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var list: ArrayList<Mentor>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentorAddBinding.inflate(layoutInflater)
        binding.apply {
            myDbHelper = MyDbHelper(root.context)
            list = myDbHelper.readMentor()
            title.text = "${MyObject.kurslar.name} Development"
            back.setOnClickListener { findNavController().popBackStack() }
            save.setOnClickListener {
                val surname = mentorSurname.text.toString()
                val name = mentorName.text.toString()
                val lastname = mentorLastname.text.toString()
                if (surname.isNotEmpty() && name.isNotEmpty() && lastname.isNotEmpty()) {
                    val mentor = Mentor(surname, name, lastname, MyObject.positionKurs)
                    myDbHelper.createMentor(mentor)
                    list.add(mentor)
                    findNavController().popBackStack()
                } else Toast.makeText(root.context, "Empty", Toast.LENGTH_SHORT).show()
            }
            return root
        }
    }
}