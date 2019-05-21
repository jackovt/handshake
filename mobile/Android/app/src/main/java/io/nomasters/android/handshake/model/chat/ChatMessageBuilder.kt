package io.nomasters.android.handshake.model.chat

import java.util.*
import javax.inject.Inject

class ChatMessageBuilder @Inject constructor() {
    fun createChatMessage(message: String, chatSession: ChatSession): ChatMessage {
        val calendar: Calendar = Calendar.getInstance()
        val now = calendar.time
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        return ChatMessage(
            message,
            chatSession.handshakeUser.id,
            now.time,
            now.time,
            calendar.timeInMillis,
            false
        )
    }


}
