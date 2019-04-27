package io.nomasters.android.handshake.ui.introduress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.nomasters.android.handshake.R
import io.nomasters.android.handshake.databinding.FragmentIntroDuressProfileBinding

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [IntroDuressProfileFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [IntroDuressProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class IntroDuressProfileFragment : Fragment() {
    private var binding: FragmentIntroDuressProfileBinding? = null
    private val callback: IntroDuressProfileFragmentActionCallback = object : IntroDuressProfileFragmentActionCallback {
        override fun onSubmitClicked() {
            navigateToLogin()
        }

        override fun onSkipClicked() {
            navigateToLogin()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro_duress_profile, container, false)
        binding?.callback = callback
        return binding?.root
    }

    private fun navigateToLogin() {
        val navController = findNavController()
        navController.navigate(R.id.loginFragment)
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
            IntroDuressProfileFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
