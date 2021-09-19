package com.russellworld.sofegram.ui.fragments.singl_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.russellworld.sofegram.R
import com.russellworld.sofegram.models.CommonModel
import com.russellworld.sofegram.utilits.*
import kotlinx.android.synthetic.main.message_item.view.*

class SingleChatAdapter : RecyclerView.Adapter<SingleChatAdapter.SingleChatHolder>() {
    override fun getItemCount(): Int = mListMessageCache.size

    var mListMessageCache = mutableListOf<CommonModel>()

    class SingleChatHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Text
        val blockUserMessage: ConstraintLayout = view.block_user_message
        val chatUserMessage: TextView = view.chat_user_message
        val chatUserMessageTime: TextView = view.chat_user__message_time
        val blockReceivedMessage: ConstraintLayout = view.block_received_message
        val chatReceivedMessage: TextView = view.chat_received_message
        val chatReceivedMessageTime: TextView = view.chat_received__message_time

        //Image
        val blockReceivedImageMessage: ConstraintLayout = view.block_received_image_message
        val blockUserImageMessage: ConstraintLayout = view.block_user_image_message
        val chatUserImage: ImageView = view.chat_user_image
        val chatReceivedImage: ImageView = view.chat_received_image
        val chatUserImageMessageTime: TextView = view.chat_user_image_message_time
        val chatReceivedImageMessageTime: TextView = view.chat_received_image_message_time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return SingleChatHolder(view)
    }

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
        when (mListMessageCache[position].type) {
            TYPE_MESSAGE_TEXT -> drawMessageText(holder, position)
            TYPE_MESSAGE_IMAGE -> drawMessageImage(holder, position)
        }
    }

    private fun drawMessageText(holder: SingleChatHolder, position: Int) {
        holder.blockUserMessage.visibility = View.GONE
        holder.blockReceivedMessage.visibility = View.GONE

        if (mListMessageCache[position].from == CURRENT_UID) {
            holder.blockUserMessage.visibility = View.VISIBLE
            holder.blockReceivedMessage.visibility = View.GONE
            holder.chatUserMessage.text = mListMessageCache[position].text
            holder.chatUserMessageTime.text =
                mListMessageCache[position].timeStamp.toString().asTime()
        } else {
            holder.blockUserMessage.visibility = View.GONE
            holder.blockReceivedMessage.visibility = View.VISIBLE
            holder.chatReceivedMessage.text = mListMessageCache[position].text
            holder.chatReceivedMessageTime.text =
                mListMessageCache[position].timeStamp.toString().asTime()
        }
    }

    private fun drawMessageImage(holder: SingleChatHolder, position: Int) {
        holder.blockUserMessage.visibility = View.GONE
        holder.blockReceivedMessage.visibility = View.GONE

        if (mListMessageCache[position].from == CURRENT_UID) {
            holder.apply {
                blockReceivedImageMessage.visibility = View.GONE
                blockUserImageMessage.visibility = View.VISIBLE
                chatUserImage.downloadAndSetImage(mListMessageCache[position].fileUrl)
                chatUserImageMessageTime.text =
                    mListMessageCache[position].timeStamp.toString().asTime()
            }
        } else {
            holder.apply {
                blockReceivedImageMessage.visibility = View.VISIBLE
                blockUserImageMessage.visibility = View.GONE
                chatReceivedImage.downloadAndSetImage(mListMessageCache[position].fileUrl)
                chatReceivedImageMessageTime.text =
                    mListMessageCache[position].timeStamp.toString().asTime()
            }
        }
    }


    fun addItemToBottom(
        item: CommonModel,
        onSuccess: () -> Unit
    ) {
        if (!mListMessageCache.contains(item)) {
            mListMessageCache.add(item)
            notifyItemInserted(mListMessageCache.size)
        }
        onSuccess()
    }

    fun addItemToTop(
        item: CommonModel,
        onSuccess: () -> Unit
    ) {
        if (!mListMessageCache.contains(item)) {
            mListMessageCache.add(item)
            mListMessageCache.sortBy { it.timeStamp.toString() }
            notifyItemInserted(mListMessageCache.size)
        }
        onSuccess()
    }

}
