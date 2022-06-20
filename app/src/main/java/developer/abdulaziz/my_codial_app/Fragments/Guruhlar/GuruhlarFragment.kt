package developer.abdulaziz.my_codial_app.Fragments.Guruhlar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.my_codial_app.Adapters.MyUniversalAdapter
import developer.abdulaziz.my_codial_app.Classes.Kurslar
import developer.abdulaziz.my_codial_app.Database.MyDbHelper
import developer.abdulaziz.my_codial_app.Object.MyObject
import developer.abdulaziz.my_codial_app.R
import developer.abdulaziz.my_codial_app.databinding.FragmentGuruhlarBinding

class GuruhlarFragment : Fragment() {
    private lateinit var binding: FragmentGuruhlarBinding
    private lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuruhlarBinding.inflate(layoutInflater)
        binding.apply {
            myDbHelper = MyDbHelper(root.context)
            rvGuruhlar.adapter = MyUniversalAdapter(
                myDbHelper.readKurslar(),
                object : MyUniversalAdapter.OnMenuClickListener {
                    override fun onClick(kurslar: Kurslar, position: Int) {
                        MyObject.kurslar = kurslar
                        MyObject.positionKurs = position
                        findNavController().navigate(R.id.gruppalarShowFragment)
                    }
                })
            back.setOnClickListener { findNavController().popBackStack() }

            return root
        }
    }
}