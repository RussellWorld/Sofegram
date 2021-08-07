package com.russellworld.sofegram.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.russellworld.sofegram.MainActivity
import com.russellworld.sofegram.R
import com.russellworld.sofegram.activities.RegisterActivity
import com.russellworld.sofegram.utilits.AUTH
import com.russellworld.sofegram.utilits.USER
import com.russellworld.sofegram.utilits.replaceActivity
import com.russellworld.sofegram.utilits.replaceFragment


class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
    }

    private fun initFields() {
        settings_bio.text = USER.bio
        settings_full_name.text = USER.fullname
        settings_phone_number.text = USER.phone
        settings_status.text = USER.status
        settings_username.text = USER.username
        settings_btn_change_username.setOnClickListener{
            replaceFragment(ChangeUserNameFragment())
        }
        settings_btn_change_bio.setOnClickListener{
            replaceFragment(ChangeBioFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
            R.id.settings_menu_change_name -> replaceFragment(ChangeNameFragment())
        }
        return true
    }

}