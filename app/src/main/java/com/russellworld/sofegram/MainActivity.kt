package com.russellworld.sofegram

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.russellworld.sofegram.activities.RegisterActivity
import com.russellworld.sofegram.databinding.ActivityMainBinding
import com.russellworld.sofegram.ui.fragments.ChatsFragment
import com.russellworld.sofegram.ui.objects.AppDrawer
import com.russellworld.sofegram.utilits.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/* Главная активность*/

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    lateinit var mToolBar: Toolbar
    lateinit var mAppDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        /* Функция запускается один раз, при создании активити */
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        initFirebase()
        initUser {
            CoroutineScope(Dispatchers.IO).launch {
                initContacts()
            }
            initFields()
            initFunc()
        }
    }

    private fun initFields() {
        /* Функция инициализирует переменные */
        mToolBar = mBinding.mainToolbar
        mAppDrawer = AppDrawer()

    }

    private fun initFunc() {
        /* Функция инициализирует функциональность приложения */
        if (AUTH.currentUser != null) {
            setSupportActionBar(mToolBar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment(), false)
        } else {
            replaceActivity(RegisterActivity())
        }
    }

    override fun onStart() {
        super.onStart()
        AppStates.updateState(AppStates.ONLINE)
    }

    override fun onStop() {
        super.onStop()
        AppStates.updateState(AppStates.OFFLINE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(APP_ACTIVITY, READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            initContacts()
        }
    }
}