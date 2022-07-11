package com.example.money_manager_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.money_manager_app.databinding.FragmentSettingsBinding

class Settings : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)

        with(binding) {
            val bundle = Bundle()
            bundle.putString(
                "BALANCE",
                (etBalance.text ?: "Error").toString()
            )
            findNavController().navigate(
                R.id.
            )
        }
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }



}