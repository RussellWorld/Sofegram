package com.russellworld.sofegram.ui.screens

import androidx.fragment.app.Fragment
import com.russellworld.sofegram.R
import com.russellworld.sofegram.utilits.APP_ACTIVITY
import com.russellworld.sofegram.utilits.hideKeyboard

/* Главный фрагмент, содержит все чаты, группы и каналы с которыми взаимодействует пользователь*/

class MainFragment : Fragment(R.layout.fragment_chats) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Sofegram"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()

    }
}