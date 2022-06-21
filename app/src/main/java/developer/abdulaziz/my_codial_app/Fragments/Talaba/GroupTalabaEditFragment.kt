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
import developer.abdulaziz.my_codial_app.databinding.FragmentGroupTalabaEditBinding

class GroupTalabaEditFragment : Fragment() {
    private lateinit var binding: FragmentGroupTalabaEditBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroupTalabaEditBinding.inflate(layoutInflater)
        binding.apply {
            val myDbHelper = MyDbHelper(root.context)

            val talaba = MyObject.talaba
            talabaSurname.setText(talaba.surname)
            talabaName.setText(talaba.name)
            talabaNumber.setText(talaba.number)
            dateText.text = talaba.regDate

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
                    talaba.surname = surname
                    talaba.name = name
                    talaba.number = number
                    talaba.regDate = date
                    myDbHelper.updateTalaba(talaba)
                    findNavController().popBackStack()
                }
            }

            return root
        }
    }
}