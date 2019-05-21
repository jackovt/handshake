package io.nomasters.android.handshake.ui.chat

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.nomasters.android.handshake.MainApplication
import io.nomasters.android.handshake.R
import io.nomasters.android.handshake.databinding.FragmentChatBinding
import io.nomasters.android.handshake.model.chat.ChatMessageBuilder
import io.nomasters.android.handshake.model.chat.ChatSession
import io.nomasters.android.handshake.ui.chat.chatmessageitem.ChatMessageViewModel
import io.nomasters.android.handshake.view.databinding.MultiTypeDataBoundAdapter
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ChatFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ChatFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 * @author JH431939 (Jack Hughes)
 * @since 4/27/19
 */
class ChatFragment : Fragment() {
    @Inject
    lateinit var chatMessageBuilder: ChatMessageBuilder
    @Inject
    lateinit var chatMessageRepo: ChatMessageBuilder
    private lateinit var binding: FragmentChatBinding
    private lateinit var viewModel: ChatViewModel
    private val callback: ChatFragmentActionCallback = object : ChatFragmentActionCallback {
        override fun sendChatMessage(message: String) {
            val chatMessage = chatMessageBuilder.createChatMessage(message, viewModel.chatSession)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(ChatViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        val chatSession = arguments?.getParcelable<ChatSession>(KEY_CHAT_SESSION)
        chatSession?.let { viewModel.chatSession = chatSession }
        val itemViewModels = chatSession?.chatMessages?.map { message ->
            ChatMessageViewModel(
                chatSession,
                message,
                chatSession.participants.find { chatParticipant -> chatParticipant.id == message.fromId }
            )
        }?.toMutableList() ?: mutableListOf()
        viewModel.adapter = MultiTypeDataBoundAdapter(itemViewModels, null)
        binding.data = viewModel
        binding.callback = callback
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApplication).mainComponent.inject(this)
    }

    companion object {
        val KEY_CHAT_SESSION = "KEY_CHAT_SESSION"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment IntroFragment.
         */
        @JvmStatic
        fun newInstance(chatSession: ChatSession) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_CHAT_SESSION, chatSession)
                }
            }
    }
}