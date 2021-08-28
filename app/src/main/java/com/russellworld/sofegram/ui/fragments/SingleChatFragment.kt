package com.russellworld.sofegram.ui.fragments

import android.view.View
import com.russellworld.sofegram.R
import com.russellworld.sofegram.models.CommonModel
import com.russellworld.sofegram.utilits.APP_ACTIVITY
import kotlinx.android.synthetic.main.activity_main.view.*


class SingleChatFragment(contact: CommonModel) : BaseFragment(R.layout.fragment_single_chat) {

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.mToolBar.toolbar_info.visibility = View.VISIBLE
    }

    override fun onPause() {
        super.onPause()
        APP_ACTIVITY.mToolBar.toolbar_info.visibility = View.GONE
    }
}