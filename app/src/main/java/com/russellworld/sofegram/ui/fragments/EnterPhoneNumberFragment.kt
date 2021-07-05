package com.russellworld.sofegram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.russellworld.sofegram.MainActivity
import com.russellworld.sofegram.R
import com.russellworld.sofegram.activities.RegisterActivity
import com.russellworld.sofegram.databinding.FragmentEnterPhoneNumberBinding
import com.russellworld.sofegram.utilits.AUTH
import com.russellworld.sofegram.utilits.replaceActivity
import com.russellworld.sofegram.utilits.replaceFragment
import com.russellworld.sofegram.utilits.showToast
import java.util.concurrent.TimeUnit

class EnterPhoneNumberFragment : Fragment() {

    private lateinit var binding: FragmentEnterPhoneNumberBinding
    private lateinit var mPhoneNumber: String
    private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks

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
        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast(getString(R.string.enter_fragment_hello))
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else showToast(it.exception?.message.toString())
                }
            }


            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(EnterCodeFragment(mPhoneNumber,id))
            }
        }

        binding.registerFab.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        if (binding.registerInputNumberPhone.text.toString().isEmpty()) {
            showToast(getString(R.string.registr_toast_enter))

        } else {
            authUser()
        }
    }

    private fun authUser() {
        mPhoneNumber = binding.registerInputNumberPhone.text.toString()

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhoneNumber,
            60,
            TimeUnit.SECONDS,
            activity as RegisterActivity,
            mCallback
        )
//        PhoneAuthProvider.verifyPhoneNumber(
//            PhoneAuthOptions
//                .newBuilder(FirebaseAuth.getInstance())
//                .setActivity(activity as RegisterActivity)
//                .setPhoneNumber(mPhoneNumber)
//                .setTimeout(60L, TimeUnit.SECONDS)
//                .setCallbacks(mCallback)
//                .build()
//        )
    }
}