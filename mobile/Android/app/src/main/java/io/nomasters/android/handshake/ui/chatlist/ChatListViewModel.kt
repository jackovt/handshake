package io.nomasters.android.handshake.ui.chatlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.nomasters.android.handshake.data.repo.chat.ChatSessionRepository
import io.nomasters.android.handshake.databinding.ItemChatListBinding
import io.nomasters.android.handshake.model.chat.ChatSession
import io.nomasters.android.handshake.ui.chatlist.chatlistitem.ChatItemViewModel
import io.nomasters.android.handshake.view.databinding.MultiTypeDataBoundAdapter

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/26/19
 */
class ChatListViewModel(
    private val chatSessionRepo: ChatSessionRepository = ChatSessionRepository()
) : ViewModel() {
    private val chatSessions: MutableLiveData<List<ChatSession>> = chatSessionRepo.chatSessions
    var adapter: MultiTypeDataBoundAdapter<ChatItemViewModel, ItemChatListBinding>? = null

    fun getChatSessions(): LiveData<List<ChatSession>> {
        return chatSessions
    }
}