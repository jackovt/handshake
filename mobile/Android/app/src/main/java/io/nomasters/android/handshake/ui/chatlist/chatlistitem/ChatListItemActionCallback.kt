package io.nomasters.android.handshake.ui.chatlist.chatlistitem

import io.nomasters.android.handshake.model.chat.ChatSession
import io.nomasters.android.handshake.view.databinding.MultiTypeDataBoundAdapter

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/12/19
 */
interface ChatListItemActionCallback : MultiTypeDataBoundAdapter.ActionCallback {
    fun chatSessionClicked(chatSession: ChatSession)
}