package com.russellworld.sofegram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.PhoneAuthProvider
import com.russellworld.sofegram.MainActivity
import com.russellworld.sofegram.R
import com.russellworld.sofegram.activities.RegisterActivity
import com.russellworld.sofegram.databinding.FragmentEnterCodeBinding
import com.russellworld.sofegram.utilits.AUTH
import com.russellworld.sofegram.utilits.AppTextWatcher
import com.russellworld.sofegram.utilits.replaceActivity
import com.russellworld.sofegram.utilits.showToast


class EnterCodeFragment(val phoneNumber: String, val id: String) : Fragment() {

    private lateinit var binding: FragmentEnterCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterCodeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = phoneNumber
        binding.registerSmsCode.addTextChangedListener(AppTextWatcher {
            val string = binding.registerSmsCode.text.toString()
            if (string.length == 6) {
                enterCode()
            }
        })

    }

    private fun enterCode() {
        val code = binding.registerSmsCode.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                showToast(getString(R.string.enter_fragment_hello))
                (activity as RegisterActivity).replaceActivity(MainActivity())
            } else showToast(it.exception?.message.toString())
        }

    }
}