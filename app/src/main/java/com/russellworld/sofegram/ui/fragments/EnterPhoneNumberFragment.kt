package com.russellworld.sofegram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.russellworld.sofegram.R
import com.russellworld.sofegram.databinding.FragmentEnterPhoneNumberBinding
import com.russellworld.sofegram.utilits.replaceFragment
import com.russellworld.sofegram.utilits.showToast

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
            showToast(getString(R.string.registr_toast_enter))

        } else {
            replaceFragment(EnterCodeFragment())
        }
    }
}