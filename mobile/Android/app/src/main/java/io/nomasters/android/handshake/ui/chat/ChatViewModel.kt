package io.nomasters.android.handshake.ui.chat

import androidx.lifecycle.ViewModel
import io.nomasters.android.handshake.databinding.ItemChatMessageBinding
import io.nomasters.android.handshake.model.chat.ChatSession
import io.nomasters.android.handshake.ui.chat.chatmessageitem.ChatMessageViewModel
import io.nomasters.android.handshake.view.databinding.MultiTypeDataBoundAdapter

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/27/19
 */
class ChatViewModel : ViewModel() {

    var adapter: MultiTypeDataBoundAdapter<ChatMessageViewModel, ItemChatMessageBinding> =
        MultiTypeDataBoundAdapter(
            mutableListOf(), null
        )
    lateinit var chatSession: ChatSession

    fun getParticipants(): String {
        return chatSession.participants.filter { it.id != chatSession.handshakeUser.id }.joinToString(
            ", "
        ) { it.name }
    }
}