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


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mToolBar: Toolbar
    private lateinit var mAppDrawer: AppDrawer

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
    }

    private fun initFun() {
        if (false) {
            setSupportActionBar(mToolBar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment())

        } else {
            replaceActivity(RegisterActivity())
        }
    }

}