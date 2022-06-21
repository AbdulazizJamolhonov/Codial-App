package developer.abdulaziz.my_codial_app.Fragments.Talaba

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.my_codial_app.Classes.Talaba
import developer.abdulaziz.my_codial_app.Database.MyDbHelper
import developer.abdulaziz.my_codial_app.Object.MyObject
import developer.abdulaziz.my_codial_app.databinding.FragmentGroupTalabaAddBinding
import java.time.LocalDate

class GroupTalabaAddFragment : Fragment() {
    private lateinit var binding: FragmentGroupTalabaAddBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroupTalabaAddBinding.inflate(layoutInflater)
        binding.apply {
            val myDbHelper = MyDbHelper(root.context)

            val month =
                if (LocalDate.now().monthValue.toString().length == 1) "0${LocalDate.now().monthValue}"
                else "${LocalDate.now().monthValue}"
            val day =
                if (LocalDate.now().dayOfMonth.toString().length == 1) "0${LocalDate.now().dayOfMonth}"
                else "${LocalDate.now().dayOfMonth}"
            dateText.text = "$day/$month/${LocalDate.now().year}"

            back.setOnClickListener { findNavController().popBackStack() }
            save.setOnClickListener {
                val surname = talabaSurname.text.toString()
                val name = talabaName.text.toString()
                val number = talabaNumber.text.toString()
                val date = dateText.text.toString()

                if (surname.isNotEmpty() && name.isNotEmpty() && number.isNotEmpty() && date.isNotEmpty()) {
                    val talaba = Talaba(
                        surname, name, number, date, MyObject.positionGuruh
                    )
                    myDbHelper.createTalaba(talaba)
                    findNavController().popBackStack()
                }
            }
            return root
        }
    }
}