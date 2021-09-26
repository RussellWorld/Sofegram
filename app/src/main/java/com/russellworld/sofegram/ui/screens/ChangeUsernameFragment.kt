package com.russellworld.sofegram.ui.screens

import com.russellworld.sofegram.R
import com.russellworld.sofegram.database.CURRENT_UID
import com.russellworld.sofegram.database.NODE_USERNAMES
import com.russellworld.sofegram.database.REF_DATABASE_ROOT
import com.russellworld.sofegram.database.USER
import com.russellworld.sofegram.utilits.*
import kotlinx.android.synthetic.main.fragment_change_user_name.*

/* Фрагмент для изменения username пользователя */

class ChangeUsernameFragment : BaseChangeFragment(R.layout.fragment_change_user_name) {
    lateinit var mNewUsername: String

    override fun onResume() {
        super.onResume()
        settings_input_username.setText(USER.username)

    }

    override fun change() {
        mNewUsername = settings_input_username.text.toString().toLowerCase()
        if (mNewUsername.isEmpty()) {
            showToast("Поле пустое")
        } else {
            REF_DATABASE_ROOT.child(NODE_USERNAMES)
                .addListenerForSingleValueEvent(AppValueEventListener {
                    if (it.hasChild(mNewUsername)) {
                        showToast("Такой пользователь уже существует")
                    } else {
                        changeUsername()
                    }
                })
        }
    }

    private fun changeUsername() {
        /* Изменение username в базе данных */
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(CURRENT_UID).setValue(CURRENT_UID)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    updateCurrentUsername(mNewUsername)
                }
            }
    }

}
