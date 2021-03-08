package com.example.stakasaki.coffeeorder.registerlogin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.stakasaki.coffeeorder.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    companion object{
        const val TAG = "Register"
    }

    private var binding: FragmentRegisterBinding? = null
    private val sharedViewModel: RegisterLoginViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        
        val fragmentBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            viewModel = sharedViewModel
            registerFragment = this@RegisterFragment
        }
    }

    fun onClickRegisterButton() {
        Log.d(TAG, "clicked a register button")
        /**
         * TODO
         *
         */
    }

    fun goBackToLogin() {
        Log.d(TAG, "clicked a text view to set up new account")
        /**
         * TODO
         */
    }

}