package io.nomasters.android.handshake.ui.chat.chatmessageitem

import android.content.Context
import androidx.lifecycle.ViewModel
import io.nomasters.android.handshake.R
import io.nomasters.android.handshake.model.chat.ChatMessage
import io.nomasters.android.handshake.model.chat.ChatParticipant
import io.nomasters.android.handshake.model.chat.ChatSession
import io.nomasters.android.handshake.util.DateTimeUtil
import io.nomasters.android.handshake.view.databinding.LayoutBinding
import java.util.*

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/27/19
 */
class ChatMessageViewModel(
    val chatSession: ChatSession,
    val chatMessage: ChatMessage,
    val chatParticipant: ChatParticipant?
) : ViewModel(),
    LayoutBinding {
    override fun getLayoutId(): Int {
        return R.layout.item_chat_message
    }

    fun getChatSenderFirstLetter(): String {
        return chatParticipant?.name?.substring(0, 1) ?: ""
    }

    fun isMessageFromHandshakeUser(): Boolean {
        return chatSession.handshakeUser.id == chatMessage.fromId
    }

    fun getExpiresTime(context: Context): String {
        return DateTimeUtil.formatTimeDifference(
            context,
            chatMessage.timestampExpires,
            Calendar.getInstance().timeInMillis
        )
    }

    fun getTimeAgo(context: Context): String {
        return DateTimeUtil.formatTimeAgo(context, Calendar.getInstance().timeInMillis, chatMessage.timestampUpdated)
    }
}