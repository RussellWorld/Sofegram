package com.russellworld.sofegram

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.russellworld.sofegram.activities.RegisterActivity
import com.russellworld.sofegram.databinding.ActivityMainBinding
import com.russellworld.sofegram.ui.fragments.ChatsFragment
import com.russellworld.sofegram.ui.objects.AppDrawer
import com.russellworld.sofegram.utilits.replaceActivity
import com.russellworld.sofegram.utilits.replaceFragment
import com.google.firebase.auth.FirebaseAuth
import com.russellworld.sofegram.utilits.AUTH
import com.russellworld.sofegram.utilits.initFirebase


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolBar: Toolbar
    lateinit var mAppDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFun()
    }

    private fun initFields() {
        mToolBar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolBar)
        initFirebase()
    }

    private fun initFun() {
        if (AUTH.currentUser != null) {
            setSupportActionBar(mToolBar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment(), false)

        } else {
            replaceActivity(RegisterActivity())
        }
    }

}