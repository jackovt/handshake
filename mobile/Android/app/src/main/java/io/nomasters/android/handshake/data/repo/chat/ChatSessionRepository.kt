package io.nomasters.android.handshake.data.repo.chat

import androidx.lifecycle.MutableLiveData
import io.nomasters.android.handshake.model.chat.ChatMessage
import io.nomasters.android.handshake.model.chat.ChatParticipant
import io.nomasters.android.handshake.model.chat.ChatSession
import java.util.*

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/27/19
 */
class ChatSessionRepository(val chatSessions: MutableLiveData<List<ChatSession>> = MutableLiveData()) {

    init {
        loadChatSessions()
    }

    private fun loadChatSessions() {
        if (chatSessions.value == null) {
            chatSessions.value = mutableListOf(createChatSession(), createChatSession(), createChatSession())
        }
    }

    private fun createChatSession(): ChatSession {
        val handshakeUser = createParticipant("5678", "Theta")
        val participants = mutableListOf(handshakeUser, createParticipant("1234", "Zircon"))
        val messages = mutableListOf(createMessage("1234"), createMessage("5678"))
        return ChatSession(handshakeUser, participants, messages)
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
        calendar.add(Calendar.DAY_OF_YEAR, 12)
        val timeInMillisExpire = calendar.timeInMillis
        return ChatMessage(messageText, fromId, timeInMillisSent, timeInMillisSent, timeInMillisExpire)
    }
}