package io.nomasters.android.handshake.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.nomasters.android.handshake.R
import io.nomasters.android.handshake.databinding.FragmentIntroBinding

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [IntroFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [IntroFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class IntroFragment : Fragment() {
    private var binding: FragmentIntroBinding? = null
    private val callback: IntroFragmentActionCallback = object : IntroFragmentActionCallback {
        override fun onLetsGetStartedClicked() {
            val navController = findNavController()
            navController.navigate(R.id.introProfileFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false)
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
            IntroFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
