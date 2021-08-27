package com.russellworld.sofegram.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.russellworld.sofegram.R
import com.russellworld.sofegram.databinding.ActivityRegisterBinding
import com.russellworld.sofegram.ui.fragments.EnterPhoneNumberFragment
import com.russellworld.sofegram.utilits.initFirebase
import com.russellworld.sofegram.utilits.replaceFragment

/* Активность для регистрации нового пользователя*/

class RegisterActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initFirebase()
    }

    override fun onStart() {
        super.onStart()
        mToolbar = mBinding.registerToolbar
        setSupportActionBar(mToolbar)
        title = getString(R.string.register_title_your_phone)
        replaceFragment(EnterPhoneNumberFragment(), false)
    }
}