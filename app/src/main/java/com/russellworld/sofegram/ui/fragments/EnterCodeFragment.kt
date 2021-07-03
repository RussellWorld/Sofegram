package com.russellworld.sofegram.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.russellworld.sofegram.databinding.FragmentEnterCodeBinding
import com.russellworld.sofegram.utilits.AppTextWatcher
import com.russellworld.sofegram.utilits.showToast


class EnterCodeFragment : Fragment() {

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
        binding.registerSmsCode.addTextChangedListener(AppTextWatcher {
            val string = binding.registerSmsCode.text.toString()
            if (string.length == 5) {
                verifiCode()
            }
        })

    }

    fun verifiCode() {
        showToast("OK")
    }

}