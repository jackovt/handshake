package io.nomasters.android.handshake.model.chat

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/26/19
 */
@Parcelize
class ChatParticipant(
    val id: String,
    val name: String
) : Parcelable {

}