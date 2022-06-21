package developer.abdulaziz.my_codial_app.Fragments.Talaba

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.my_codial_app.Classes.Talaba
import developer.abdulaziz.my_codial_app.Database.MyDbHelper
import developer.abdulaziz.my_codial_app.Object.MyObject
import developer.abdulaziz.my_codial_app.databinding.FragmentGroupTalabaAddBinding

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
            talabaDate.setOnClickListener {
                val dataPickerDialog = DatePickerDialog(root.context)
                dataPickerDialog.setOnDateSetListener { datePicker, i, i2, i3 ->
                    dateText.text = "$i3/${i2 + 1}/$i"
                }
                dataPickerDialog.show()
            }
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