package com.russellworld.sofegram.ui.fragments

import androidx.fragment.app.Fragment
import com.russellworld.sofegram.R
import com.russellworld.sofegram.utilits.APP_ACTIVITY

/* Главный фрагмент, содержит все чаты, группы и каналы с которыми взаимодействует пользователь*/

class ChatsFragment : Fragment(R.layout.fragment_chats) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Чаты"

    }
}