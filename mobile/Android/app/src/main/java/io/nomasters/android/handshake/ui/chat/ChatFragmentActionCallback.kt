package io.nomasters.android.handshake.ui.chat

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/27/19
 */
interface ChatFragmentActionCallback : TextView.OnEditorActionListener {

    fun sendChatMessage(message: String)

    override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            sendChatMessage(v.text.toString())
            v.text = ""
            return true
        }
        return false
    }
}