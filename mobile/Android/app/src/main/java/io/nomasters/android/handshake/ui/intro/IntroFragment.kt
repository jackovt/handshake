package io.nomasters.android.handshake.ui.intro

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
    private var listener: OnFragmentInteractionListener? = null
    private var binding: FragmentIntroBinding? = null
    private val callback: IntroFragmentActionCallback = object : IntroFragmentActionCallback {
        override fun onLetsGetStartedClicked() {
            listener?.navigateToProfileCreation()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false)
        binding?.callback = callback
        return binding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun navigateToProfileCreation()
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
