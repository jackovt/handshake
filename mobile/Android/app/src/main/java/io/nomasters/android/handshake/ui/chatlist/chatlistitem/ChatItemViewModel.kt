package io.nomasters.android.handshake.ui.chatlist.chatlistitem

import io.nomasters.android.handshake.R
import io.nomasters.android.handshake.model.chat.ChatSession
import io.nomasters.android.handshake.view.databinding.LayoutBinding

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/26/19
 */
class ChatItemViewModel(val chatSession: ChatSession) : LayoutBinding {
    override fun getLayoutId(): Int {
        return R.layout.item_chat_list
    }
}