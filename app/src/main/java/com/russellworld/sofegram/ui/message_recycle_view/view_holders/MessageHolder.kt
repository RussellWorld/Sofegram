package com.russellworld.sofegram.ui.message_recycle_view.view_holders

import com.russellworld.sofegram.ui.message_recycle_view.views.MessageView

interface MessageHolder {
    fun drawMessage(view: MessageView)
    fun onAttach(view: MessageView)
    fun onDetach()
}