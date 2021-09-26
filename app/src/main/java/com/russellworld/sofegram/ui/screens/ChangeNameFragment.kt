package com.russellworld.sofegram.ui.screens

import com.russellworld.sofegram.R
import com.russellworld.sofegram.database.USER
import com.russellworld.sofegram.utilits.*
import kotlinx.android.synthetic.main.fragment_change_name.*

/* Фрагмент для изменения имени пользователя */

class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {
    override fun onResume() {
        super.onResume()
        initFullNameList()
    }

    private fun initFullNameList() {
        val fullnameList = USER.fullname.split(" ")
        if (fullnameList.size > 1) {
            settings_input_name.setText(fullnameList[0])
            settings_input_surname.setText(fullnameList[1])
        } else settings_input_name.setText(fullnameList[0])
    }


    override fun change() {
        val name = R.id.settings_input_name.toString()
        val surname = R.id.settings_input_surname.toString()
        if (name.isEmpty()) {
            showToast(getString(R.string.settings_toast_name_is_empty))
        } else {
            val fullname = "$name $surname"
            setNameToDatabase(fullname)
        }

    }

}