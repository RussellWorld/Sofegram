package com.russellworld.sofegram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.russellworld.sofegram.R
import com.russellworld.sofegram.databinding.FragmentEnterPhoneNumberBinding

class EnterPhoneNumberFragment : Fragment() {

    private lateinit var binding: FragmentEnterPhoneNumberBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterPhoneNumberBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.registerFab.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        if (binding.registerInputNumberPhone.text.toString().isEmpty()) {
            Toast.makeText(activity, getString(R.string.registr_toast_enter), Toast.LENGTH_SHORT)
                .show()

        } else {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.registerDataContainer, EnterCodeFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}