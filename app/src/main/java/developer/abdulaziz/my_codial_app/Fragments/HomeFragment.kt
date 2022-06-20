package developer.abdulaziz.my_codial_app.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.my_codial_app.R
import developer.abdulaziz.my_codial_app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.apply {
            kurslar.setOnClickListener { findNavController().navigate(R.id.kurslarFragment) }
            mentorlar.setOnClickListener { findNavController().navigate(R.id.mentorlarFragment) }
            guruhlar.setOnClickListener { findNavController().navigate(R.id.guruhlarFragment) }

            return root
        }
    }
}