package io.nomasters.android.handshake.ui.chatlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.nomasters.android.handshake.databinding.ItemChatListBinding
import io.nomasters.android.handshake.model.chat.ChatMessage
import io.nomasters.android.handshake.model.chat.ChatParticipant
import io.nomasters.android.handshake.model.chat.ChatSession
import io.nomasters.android.handshake.ui.chatlist.chatlistitem.ChatItemViewModel
import io.nomasters.android.handshake.view.databinding.MultiTypeDataBoundAdapter
import java.util.*

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/26/19
 */
class ChatListViewModel(
    private val chatSessions: MutableLiveData<MutableList<ChatSession>> = MutableLiveData()
) : ViewModel() {
    var adapter: MultiTypeDataBoundAdapter<ChatItemViewModel, ItemChatListBinding>? = null

    init {
        loadChatSessions()
    }

    fun getChatSessions(): LiveData<MutableList<ChatSession>> {
        return chatSessions
    }

    private fun loadChatSessions() {
        if (chatSessions.value == null) {
            chatSessions.value = mutableListOf(createChatSession(), createChatSession(), createChatSession())
        }
    }

    private fun createChatSession(): ChatSession {
        val participants = mutableListOf(createParticipant("1234", "Zircon"), createParticipant("5678", "Theta"))
        val messages = mutableListOf(createMessage("1234"), createMessage("5678"))
        return ChatSession(participants, messages)
    }

    private fun createParticipant(id: String, name: String): ChatParticipant {
        return ChatParticipant(id, name)
    }

    private fun createMessage(fromId: String): ChatMessage {
        val messageText =
            "Really long message that talks about things. Lots of good content and stuff. Things that are said in a chat by human persons."
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MINUTE, -12)
        val timeInMillisSent = calendar.timeInMillis
        return ChatMessage(messageText, fromId, timeInMillisSent, timeInMillisSent)
    }
}