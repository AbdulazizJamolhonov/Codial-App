package developer.abdulaziz.my_codial_app.Fragments.Mentorlar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.my_codial_app.Adapters.MyUniversalAdapter
import developer.abdulaziz.my_codial_app.Classes.Kurslar
import developer.abdulaziz.my_codial_app.Database.MyDbHelper
import developer.abdulaziz.my_codial_app.Object.MyObject
import developer.abdulaziz.my_codial_app.R
import developer.abdulaziz.my_codial_app.databinding.FragmentMentorlarBinding

class MentorlarFragment : Fragment() {
    private lateinit var binding: FragmentMentorlarBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var listKurslar: ArrayList<Kurslar>
    private lateinit var mentorAdapter: MyUniversalAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentorlarBinding.inflate(layoutInflater)
        binding.apply {
            myDbHelper = MyDbHelper(root.context)
            listKurslar = myDbHelper.readKurslar()
            mentorAdapter =
                MyUniversalAdapter(listKurslar, object : MyUniversalAdapter.OnMenuClickListener {
                    @SuppressLint("SetTextI18n")
                    override fun onClick(kurslar: Kurslar, position: Int) {
                        MyObject.kurslar = kurslar
                        MyObject.positionKurs = position
                        findNavController().navigate(R.id.mentorShowFragment)
                    }
                })
            rvMentorlar.adapter = mentorAdapter
            back.setOnClickListener { findNavController().popBackStack() }

            return root
        }
    }
}