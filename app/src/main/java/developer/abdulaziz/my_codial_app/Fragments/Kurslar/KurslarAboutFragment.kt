package developer.abdulaziz.my_codial_app.Fragments.Kurslar

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.my_codial_app.Database.MyDbHelper
import developer.abdulaziz.my_codial_app.Object.MyObject
import developer.abdulaziz.my_codial_app.databinding.FragmentKurslarAboutBinding
import developer.abdulaziz.my_codial_app.databinding.ItemDeleteBinding

class KurslarAboutFragment : Fragment() {
    private lateinit var binding: FragmentKurslarAboutBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKurslarAboutBinding.inflate(layoutInflater)
        binding.apply {
            val myDbHelper = MyDbHelper(root.context)
            val kurslar = MyObject.kurslar
            val position = MyObject.positionKurs
            title.text = "${kurslar.name} Development"
            back.setOnClickListener { findNavController().popBackStack() }
            textAbout.text = kurslar.about
            deleteKurs.setOnClickListener {
                val alertDialog = AlertDialog.Builder(root.context).create()
                val item = ItemDeleteBinding.inflate(layoutInflater).apply {
                    save.setOnClickListener {
                        for (mentor in myDbHelper.readMentor()) {
                            if (mentor.myKurs == position)
                                myDbHelper.deleteMentor(mentor)
                        }

//                        for (talaba in myDbHelper.readTalaba()) {
//                            for (talabaGroup in myDbHelper.readGroup()) {
//                                if (talaba.myGuruh == myDbHelper.readGroup().indexOf(talabaGroup))
//                                    myDbHelper.deleteTalaba(talaba)
//                            }
//                        }
//
//                        for (group in myDbHelper.readGroup()) {
//                            if (group.myKurs == position)
//                                myDbHelper.deleteGroup(group)
//                        }

                        myDbHelper.deleteKurslar(kurslar)
                        alertDialog.cancel()
                        findNavController().popBackStack()
                    }
                    close.setOnClickListener { alertDialog.cancel() }
                }
                alertDialog.setView(item.root)
                alertDialog.show()
            }

            return root
        }
    }
}