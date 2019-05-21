package io.nomasters.android.handshake.ui.chatlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import io.nomasters.android.handshake.MainApplication
import io.nomasters.android.handshake.R
import io.nomasters.android.handshake.databinding.FragmentChatListBinding
import io.nomasters.android.handshake.model.chat.ChatSession
import io.nomasters.android.handshake.ui.chat.ChatFragment
import io.nomasters.android.handshake.ui.chatlist.chatlistitem.ChatItemViewModel
import io.nomasters.android.handshake.ui.chatlist.chatlistitem.ChatListItemActionCallback
import io.nomasters.android.handshake.view.databinding.MultiTypeDataBoundAdapter
import io.nomasters.android.handshake.view.viewmodel.DaggerViewModelFactory
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ChatListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ChatListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ChatListFragment : Fragment() {
    private var binding: FragmentChatListBinding? = null
    @Inject
    lateinit var viewModeFactory: DaggerViewModelFactory
    private var viewModel: ChatListViewModel? = null
    private val callback: ChatListFragmentActionCallback = object : ChatListFragmentActionCallback {

    }
    private val chatSessionItemCallback: ChatListItemActionCallback = object : ChatListItemActionCallback {
        override fun chatSessionClicked(chatSession: ChatSession) {
            navigateToChatSession(chatSession)
        }

    }

    private fun navigateToChatSession(chatSession: ChatSession) {
        val args = Bundle().apply {
            putParcelable(ChatFragment.KEY_CHAT_SESSION, chatSession)
        }
        findNavController().navigate(R.id.chatFragment, args)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_list, container, false)
        binding?.data = viewModel
        binding?.callback = callback
        return binding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MainApplication).mainComponent.inject(this)
        viewModel = activity?.run {
            ViewModelProviders.of(this, viewModeFactory).get(ChatListViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        viewModel?.let { viewModel ->
            viewModel.getChatSessions().observe(this, Observer<List<ChatSession>> {
                val chatItemModels = it.map { ChatItemViewModel(it) }.toMutableList()
                viewModel.adapter = MultiTypeDataBoundAdapter(chatItemModels, chatSessionItemCallback)
            })
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment IntroFragment.
         */
        @JvmStatic
        fun newInstance() =
            ChatListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
