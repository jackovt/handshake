package io.nomasters.android.handshake.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.nomasters.android.handshake.R
import io.nomasters.android.handshake.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoginFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LoginFragment : Fragment() {
    private var binding: FragmentLoginBinding? = null
    private val callback: LoginFragmentActionCallback = object : LoginFragmentActionCallback {
        override fun onLoginClicked() {
            login()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding?.callback = callback
        return binding?.root
    }

    private fun login() {
        // TODO login
        if (tryLogin("")) {
            navigateToChatListFragment()
        }
    }

    private fun navigateToChatListFragment() {
        findNavController().navigate(R.id.chatListFragment)
    }

    private fun tryLogin(password: String): Boolean {
        return true
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
            LoginFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
