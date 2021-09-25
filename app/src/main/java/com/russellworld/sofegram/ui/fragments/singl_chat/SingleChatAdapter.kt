package com.russellworld.sofegram.ui.fragments.singl_chat

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.russellworld.sofegram.ui.fragments.message_recycle_view.view_holders.AppHolderFactory
import com.russellworld.sofegram.ui.fragments.message_recycle_view.view_holders.HolderImageMessage
import com.russellworld.sofegram.ui.fragments.message_recycle_view.view_holders.HolderTextMessage
import com.russellworld.sofegram.ui.fragments.message_recycle_view.view_holders.HolderVoiceMessage
import com.russellworld.sofegram.ui.fragments.message_recycle_view.views.MessageView

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
            is HolderImageMessage -> holder.drawMessageImage(holder, mListMessageCache[position])
            is HolderVoiceMessage -> holder.drawMessageVoice(holder, mListMessageCache[position])
            is HolderTextMessage -> holder.drawMessageText(holder, mListMessageCache[position])
            else -> {

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
