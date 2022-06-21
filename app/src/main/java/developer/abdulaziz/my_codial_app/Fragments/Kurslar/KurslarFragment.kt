package developer.abdulaziz.my_codial_app.Fragments.Kurslar

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.my_codial_app.Adapters.MyUniversalAdapter
import developer.abdulaziz.my_codial_app.Classes.Kurslar
import developer.abdulaziz.my_codial_app.Database.MyDbHelper
import developer.abdulaziz.my_codial_app.Object.MyObject
import developer.abdulaziz.my_codial_app.R
import developer.abdulaziz.my_codial_app.databinding.FragmentKurslarBinding
import developer.abdulaziz.my_codial_app.databinding.ItemKurslarDialogBinding

class KurslarFragment : Fragment() {
    private lateinit var binding: FragmentKurslarBinding
    private lateinit var list: ArrayList<Kurslar>
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var kurslarAdapterMy: MyUniversalAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKurslarBinding.inflate(layoutInflater)
        binding.apply {
            myDbHelper = MyDbHelper(root.context)
            list = myDbHelper.readKurslar()
            addKurs.setOnClickListener {
                val alertDialog = AlertDialog.Builder(root.context).create()
                val item = ItemKurslarDialogBinding.inflate(layoutInflater).apply {
                    save.setOnClickListener {
                        val name = kursNomi.text.toString()
                        val about = kursHaqida.text.toString()
                        if (name.isNotEmpty() &&
                            about.isNotEmpty()
                        ) {
                            val kurslar = Kurslar(name, about)
                            myDbHelper.createKurslar(kurslar)
                            list.add(kurslar)
                            kurslarAdapterMy.notifyItemInserted(list.indexOf(kurslar))
                            alertDialog.cancel()
                        } else Toast.makeText(root.context, "Empty", Toast.LENGTH_SHORT).show()
                    }
                    close.setOnClickListener { alertDialog.cancel() }
                }
                alertDialog.setView(item.root)
                alertDialog.show()
            }
            kurslarAdapterMy = MyUniversalAdapter(list, object : MyUniversalAdapter.OnMenuClickListener {
                override fun onClick(kurslar: Kurslar, position: Int) {
                    MyObject.kurslar = kurslar
                    MyObject.positionKurs = position
                    findNavController().navigate(R.id.kurslarAboutFragment)
                }
            })
            rvKurslar.adapter = kurslarAdapterMy
            back.setOnClickListener { findNavController().popBackStack() }
            return root
        }
    }
}