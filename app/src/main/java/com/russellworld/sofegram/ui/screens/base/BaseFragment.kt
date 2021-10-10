package com.russellworld.sofegram.ui.screens.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.russellworld.sofegram.utilits.APP_ACTIVITY

/* Базовый фрагмент, от него наследуются все фрагменты приложения, кроме главного */

open class BaseFragment(val layout: Int) : Fragment() {
    private lateinit var mRootView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(layout, container, false)
        return mRootView
    }

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.mAppDrawer.disableDrawer()

    }

}