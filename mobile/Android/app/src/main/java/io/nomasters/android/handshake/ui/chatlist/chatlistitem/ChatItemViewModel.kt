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

    fun getChatSenderFirstLetter(): String {
        val oldestChatMessage = chatSession.chatMessages
            .filter { chatMessage -> chatMessage.fromId != chatSession.handshakeUser.id }
            .maxBy { chatMessage -> chatMessage.timestampUpdated }
        return oldestChatMessage?.let {
            chatSession.participants.find { participant -> participant.id == it.fromId }?.name?.substring(0, 1) ?: ""
        } ?: ""
    }
}