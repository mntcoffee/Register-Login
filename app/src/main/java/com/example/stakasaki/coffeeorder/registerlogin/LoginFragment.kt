package com.example.stakasaki.coffeeorder.registerlogin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.stakasaki.coffeeorder.R
import com.example.stakasaki.coffeeorder.databinding.FragmentLoginBinding

/**
 * [LoginFragment] allows a user to login
 */

class LoginFragment : Fragment() {

    companion object {
        const val TAG = "Login"
    }

    private var binding: FragmentLoginBinding? = null
    private val sharedViewModel: RegisterLoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentLoginBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // bind variables in its layout
        binding?.apply {
            viewModel = sharedViewModel
            loginFragment = this@LoginFragment
        }
    }

    // prevent from memory leak
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    // a helper method for login button
    fun onClickLoginButton() {
        Log.d(TAG, "clicked a login button")
        
    }

    // a helper method for setup
    fun goToSetup() {
        Log.d(TAG, "clicked a text view to set up new account")
        // go to register fragment
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }
    
            
}