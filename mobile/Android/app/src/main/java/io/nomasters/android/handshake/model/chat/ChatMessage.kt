package io.nomasters.android.handshake.model.chat

import android.content.Context
import android.os.Parcelable
import io.nomasters.android.handshake.util.DateTimeUtil
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/26/19
 */
@Parcelize
class ChatMessage(
    val messageText: String,
    val fromId: String,
    val timestampSent: Long,
    val timestampUpdated: Long,
    val timestampExpires: Long,
    val duress: Boolean = false
) : Parcelable {

    fun getSentTime(): String {
        return DateTimeUtil.formatTimeByClosestTimeOrDate(timestampSent)
    }
}