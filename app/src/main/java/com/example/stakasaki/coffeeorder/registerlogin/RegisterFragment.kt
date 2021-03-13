package com.example.stakasaki.coffeeorder.registerlogin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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

        // bind variables in its layout
        binding?.apply {
            viewModel = sharedViewModel
            registerFragment = this@RegisterFragment
        }
    }

    // prevent from memory leak
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    // a helper method for register button
    fun onClickRegisterButton() {
        Log.d(TAG, "clicked a register button")
        activity?.let { sharedViewModel.performRegister(
            it,
            binding?.mailInputTextRegister?.text.toString(),
            binding?.passwordInputTextRegister?.text.toString(),
            binding?.usernameInputTextRegister?.text.toString())
        }
    }

    // a helper method for going back to login fragment
    fun goBackToLogin() {
        Log.d(TAG, "clicked a text view to set up new account")
        findNavController().navigateUp()
    }

}