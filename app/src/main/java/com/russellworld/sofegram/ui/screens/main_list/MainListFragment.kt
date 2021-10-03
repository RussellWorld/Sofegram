package com.russellworld.sofegram.ui.screens.main_list

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.russellworld.sofegram.R
import com.russellworld.sofegram.database.*
import com.russellworld.sofegram.models.CommonModel
import com.russellworld.sofegram.utilits.APP_ACTIVITY
import com.russellworld.sofegram.utilits.AppValueEventListener
import com.russellworld.sofegram.utilits.getCommonModel
import com.russellworld.sofegram.utilits.hideKeyboard
import kotlinx.android.synthetic.main.fragment_main_list.*

/* Главный фрагмент, содержит все чаты, группы и каналы с которыми взаимодействует пользователь*/

class MainListFragment : Fragment(R.layout.fragment_main_list) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainListAdapter
    private val mRefMailList = REF_DATABASE_ROOT.child(NODE_MAIN_LIST).child(CURRENT_UID)
    private val mRefUsers = REF_DATABASE_ROOT.child(NODE_USERS)
    private val mRefMessages = REF_DATABASE_ROOT.child(NODE_MESSAGES).child(CURRENT_UID)
    private var mListItems = listOf<CommonModel>()

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Sofegram"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
        iniRecyclerView()

    }

    private fun iniRecyclerView() {
        mRecyclerView = main_list_recycle_view
        mAdapter = MainListAdapter()

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
                            newModel.lastMessage = tempList[0].text

                            if (newModel.fullname.isEmpty()){
                                newModel.fullname = newModel.phone
                            }
                            mAdapter.updateListItems(newModel)
                        })
                })
            }
        })
        mRecyclerView.adapter = mAdapter

    }
}