package com.russellworld.sofegram.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.russellworld.sofegram.databinding.FragmentEnterCodeBinding


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
        binding.registerSmsCode.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                val string = binding.registerSmsCode.text.toString()
                if (string.length==6){

                }
            }

        })

    }

    fun verifiCode(){
        Toast.makeText(activity, "Окей", Toast.LENGTH_SHORT).show()
    }

}