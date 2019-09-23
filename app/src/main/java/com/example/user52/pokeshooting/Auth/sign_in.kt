package com.example.user52.pokeshooting.Auth

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.user52.pokeshooting.R
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [sign_in.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [sign_in.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class sign_in : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseAuth.getInstance().signOut()

        val bt_option1 = view.findViewById<Button>(R.id.signup)
        bt_option1?.setOnClickListener {
            SingUpPressed(it)
        }

        val bt_option2 = view.findViewById<Button>(R.id.firebase)
        bt_option2?.setOnClickListener {
            SignInFirebase(it)
        }
        val bt_option3 = view.findViewById<SignInButton>(R.id.google)
        bt_option3?.setOnClickListener {
            SignInGoogle(it)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: View) {
        if (listener != null){
            listener?.onFragmentInteraction(uri)
        }
    }

    fun SingUpPressed(uri: View){
        if (listener != null){
            listener?.SingUpPressed(uri)
        }
    }

    fun SignInFirebase(uri: View){
        if (listener != null){
            listener?.SigninFirebase(uri)
        }
    }

    fun SignInGoogle(uri: View){
        if (listener != null){
            listener?.SigninGoogle(uri)
        }
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
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: View)
        fun SingUpPressed(uri: View)
        fun SigninFirebase(uri: View)
        fun SigninGoogle(uri: View)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment sign_in.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                sign_in().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
