package com.russellworld.sofegram.ui.screens.settings

import com.russellworld.sofegram.R
import com.russellworld.sofegram.database.USER
import com.russellworld.sofegram.ui.screens.base.BaseChangeFragment
import com.russellworld.sofegram.utilits.*
import kotlinx.android.synthetic.main.fragment_change_bio.*

/* Фрагмент для изменения информации о пользователе */

class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settings_input_bio.setText(USER.bio)
    }


    override fun change() {
        super.change()
        val newBio = settings_input_bio.text.toString()
        setBioToDatabase(newBio)
    }

}