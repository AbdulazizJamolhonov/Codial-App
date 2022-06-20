package developer.abdulaziz.my_codial_app.Fragments.Kurslar

import android.app.DatePickerDialog
import android.content.Context

class TalabaQoshishFragment {
    fun m(context: Context) {
        val dataPickerDialog = DatePickerDialog(context)
        dataPickerDialog.setOnDateSetListener { datePicker, i, i2, i3 ->
            val text = "$i3/${i2 + 1}/$i"
        }
        dataPickerDialog.show()
    }
}