package com.russellworld.sofegram.ui.fragments

import com.russellworld.sofegram.R
import com.russellworld.sofegram.utilits.APP_ACTIVITY


class ContactsFragment : BaseFragment(R.layout.fragment_contacts) {
    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Контакты"
    }

}