package io.nomasters.android.handshake.model.chat

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/26/19
 */
class ChatSession(
    val participants: List<ChatParticipant>,
    val chatMessages: List<ChatMessage>
    ) {
}