package com.russellworld.sofegram.ui.fragments.message_recycle_view.view_holders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.russellworld.sofegram.database.CURRENT_UID
import com.russellworld.sofegram.ui.fragments.message_recycle_view.views.MessageView
import com.russellworld.sofegram.utilits.asTime
import kotlinx.android.synthetic.main.message_item_text.view.*

class HolderTextMessage(view: View) : RecyclerView.ViewHolder(view) {

    val blockUserMessage: ConstraintLayout = view.block_user_message
    val chatUserMessage: TextView = view.chat_user_message
    val chatUserMessageTime: TextView = view.chat_user__message_time
    val blockReceivedMessage: ConstraintLayout = view.block_received_message
    val chatReceivedMessage: TextView = view.chat_received_message
    val chatReceivedMessageTime: TextView = view.chat_received__message_time

    fun drawMessageText(holder: HolderTextMessage, view: MessageView) {
        if (view.from == CURRENT_UID) {
            holder.blockUserMessage.visibility = View.VISIBLE
            holder.blockReceivedMessage.visibility = View.GONE
            holder.chatUserMessage.text = view.text
            holder.chatUserMessageTime.text =
                view.timeStamp.asTime()
        } else {
            holder.blockUserMessage.visibility = View.GONE
            holder.blockReceivedMessage.visibility = View.VISIBLE
            holder.chatReceivedMessage.text = view.text
            holder.chatReceivedMessageTime.text =
                view.timeStamp.asTime()
        }
    }
}