package com.russellworld.sofegram.ui.fragments.singl_chat

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.russellworld.sofegram.database.CURRENT_UID
import com.russellworld.sofegram.ui.fragments.message_recycle_view.view_holders.AppHolderFactory
import com.russellworld.sofegram.ui.fragments.message_recycle_view.view_holders.HolderImageMessage
import com.russellworld.sofegram.ui.fragments.message_recycle_view.view_holders.HolderTextMessage
import com.russellworld.sofegram.ui.fragments.message_recycle_view.views.MessageView
import com.russellworld.sofegram.utilits.asTime
import com.russellworld.sofegram.utilits.downloadAndSetImage

class SingleChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int = mListMessageCache.size

    var mListMessageCache = mutableListOf<MessageView>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AppHolderFactory.getHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return mListMessageCache[position].getTypeView()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HolderImageMessage -> drawMessageImage(holder, position)
            is HolderTextMessage -> drawMessageText(holder, position)
            else -> {

            }
        }
    }

    private fun drawMessageText(holder: HolderTextMessage, position: Int) {
        if (mListMessageCache[position].from == CURRENT_UID) {
            holder.blockUserMessage.visibility = View.VISIBLE
            holder.blockReceivedMessage.visibility = View.GONE
            holder.chatUserMessage.text = mListMessageCache[position].text
            holder.chatUserMessageTime.text =
                mListMessageCache[position].timeStamp.asTime()
        } else {
            holder.blockUserMessage.visibility = View.GONE
            holder.blockReceivedMessage.visibility = View.VISIBLE
            holder.chatReceivedMessage.text = mListMessageCache[position].text
            holder.chatReceivedMessageTime.text =
                mListMessageCache[position].timeStamp.asTime()
        }
    }

    private fun drawMessageImage(holder: HolderImageMessage, position: Int) {

        if (mListMessageCache[position].from == CURRENT_UID) {
            holder.apply {
                blockReceivedImageMessage.visibility = View.GONE
                blockUserImageMessage.visibility = View.VISIBLE
                chatUserImage.downloadAndSetImage(mListMessageCache[position].fileUrl)
                chatUserImageMessageTime.text =
                    mListMessageCache[position].timeStamp.asTime()
            }
        } else {
            holder.apply {
                blockReceivedImageMessage.visibility = View.VISIBLE
                blockUserImageMessage.visibility = View.GONE
                chatReceivedImage.downloadAndSetImage(mListMessageCache[position].fileUrl)
                chatReceivedImageMessageTime.text =
                    mListMessageCache[position].timeStamp.asTime()
            }
        }
    }


    fun addItemToBottom(
        item: MessageView,
        onSuccess: () -> Unit
    ) {
        if (!mListMessageCache.contains(item)) {
            mListMessageCache.add(item)
            notifyItemInserted(mListMessageCache.size)
        }
        onSuccess()
    }

    fun addItemToTop(
        item: MessageView,
        onSuccess: () -> Unit
    ) {
        if (!mListMessageCache.contains(item)) {
            mListMessageCache.add(item)
            mListMessageCache.sortBy { it.timeStamp }
            notifyItemInserted(mListMessageCache.size)
        }
        onSuccess()
    }

}
