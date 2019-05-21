package io.nomasters.android.handshake.data.repo.chat

import androidx.lifecycle.MutableLiveData
import io.nomasters.android.handshake.model.chat.ChatSession

/**
 * @author JH431939 (Jack Hughes)
 * @since 5/5/19
 */
interface ChatSessionRepository {
    var chatSessions: MutableLiveData<List<ChatSession>>
}