package com.russellworld.sofegram.ui.screens.groups

import androidx.recyclerview.widget.RecyclerView
import com.russellworld.sofegram.R
import com.russellworld.sofegram.database.*
import com.russellworld.sofegram.models.CommonModel
import com.russellworld.sofegram.ui.screens.base.BaseFragment
import com.russellworld.sofegram.utilits.*
import kotlinx.android.synthetic.main.fragment_add_contacts.*

/* Главный фрагмент, содержит все чаты, группы и каналы с которыми взаимодействует пользователь*/

class AddContactsFragment : BaseFragment(R.layout.fragment_contacts) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: AddContactsAdapter
    private val mRefMailList = REF_DATABASE_ROOT.child(NODE_MAIN_LIST).child(CURRENT_UID)
    private val mRefUsers = REF_DATABASE_ROOT.child(NODE_USERS)
    private val mRefMessages = REF_DATABASE_ROOT.child(NODE_MESSAGES).child(CURRENT_UID)
    private var mListItems = listOf<CommonModel>()

    override fun onResume() {
        super.onResume()
        listContacts.clear()
        APP_ACTIVITY.title = "Добавить участника"
        hideKeyboard()
        iniRecyclerView()
        add_contacts_btn_next.setOnClickListener {
            if (listContacts.isEmpty()) showToast("Добавьте участников")
            else replaceFragment(CreateGroupFragment(listContacts))
        }
    }


    private fun iniRecyclerView() {
        mRecyclerView = add_contacts_recycle_view
        mAdapter = AddContactsAdapter()

        // 1 запрос
        mRefMailList.addListenerForSingleValueEvent(AppValueEventListener { dataSnapshot ->
            mListItems = dataSnapshot.children.map { it.getCommonModel() }
            mListItems.forEach { model ->

                // 2 запрос
                mRefUsers.child(model.id).addListenerForSingleValueEvent(AppValueEventListener { dataSnapshot1 ->
                    val newModel = dataSnapshot1.getCommonModel()

                    // 3 запрос
                    mRefMessages.child(model.id).limitToLast(1)
                        .addListenerForSingleValueEvent(AppValueEventListener { dataSnapshot2 ->
                            val tempList = dataSnapshot2.children.map { it.getCommonModel() }
                            if (tempList.isEmpty()) {
                                newModel.lastMessage = "Чат очищен"
                            } else {
                                newModel.lastMessage = tempList[0].text
                            }
                            if (newModel.fullname.isEmpty()) {
                                newModel.fullname = newModel.phone
                            }
                            mAdapter.updateListItems(newModel)
                        })
                })
            }
        })
        mRecyclerView.adapter = mAdapter
    }

    companion object {
        val listContacts = mutableListOf<CommonModel>()
    }
}