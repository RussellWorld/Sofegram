package com.russellworld.sofegram.ui.screens.groups

import androidx.recyclerview.widget.RecyclerView
import com.russellworld.sofegram.R
import com.russellworld.sofegram.models.CommonModel
import com.russellworld.sofegram.ui.screens.base.BaseFragment
import com.russellworld.sofegram.utilits.APP_ACTIVITY
import com.russellworld.sofegram.utilits.hideKeyboard
import com.russellworld.sofegram.utilits.showToast
import kotlinx.android.synthetic.main.fragment_create_group.*

class CreateGroupFragment(var listContacts: List<CommonModel>) : BaseFragment(R.layout.fragment_create_group) {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: AddContactsAdapter

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Создать группу"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
        iniRecyclerView()
        create_group_btn_complete.setOnClickListener {
            showToast("Click")
        }
        create_group_input_name.requestFocus()
    }


    private fun iniRecyclerView() {
        mRecyclerView = create_group_recycle_view
        mAdapter = AddContactsAdapter()
        mRecyclerView.adapter = mAdapter
        listContacts.forEach { mAdapter.updateListItems(it) }
    }
}