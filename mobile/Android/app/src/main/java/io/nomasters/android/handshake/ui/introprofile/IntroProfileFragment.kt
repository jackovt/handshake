package io.nomasters.android.handshake.ui.introprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.nomasters.android.handshake.R
import io.nomasters.android.handshake.databinding.FragmentIntroProfileBinding

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [IntroProfileFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [IntroProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class IntroProfileFragment : Fragment() {
    private var binding: FragmentIntroProfileBinding? = null
    private val callback: IntroProfileFragmentActionCallback = object : IntroProfileFragmentActionCallback {
        override fun onSubmitClicked() {
            val navController = findNavController()
            navController.navigate(R.id.introDuressProfileFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro_profile, container, false)
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
            IntroProfileFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
