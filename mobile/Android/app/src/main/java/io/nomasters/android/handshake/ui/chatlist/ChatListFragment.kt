package io.nomasters.android.handshake.ui.chatlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.nomasters.android.handshake.R
import io.nomasters.android.handshake.databinding.FragmentChatListBinding
import io.nomasters.android.handshake.model.chat.ChatSession
import io.nomasters.android.handshake.ui.chatlist.chatlistitem.ChatItemViewModel
import io.nomasters.android.handshake.view.databinding.MultiTypeDataBoundAdapter

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
    private var viewModel: ChatListViewModel? = null
    private val callback: ChatListFragmentActionCallback = object : ChatListFragmentActionCallback {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_list, container, false)
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(ChatListViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        viewModel?.let { viewModel ->
            viewModel.getChatSessions().observe(this, Observer<MutableList<ChatSession>> {
                val chatItemModels = it.map { ChatItemViewModel(it) }.toMutableList()
                viewModel.adapter = MultiTypeDataBoundAdapter(chatItemModels, null)
            })
        }
        binding?.data = viewModel
        binding?.callback = callback
        return binding?.root
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
