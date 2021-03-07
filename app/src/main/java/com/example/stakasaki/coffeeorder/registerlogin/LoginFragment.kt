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
import com.example.stakasaki.coffeeorder.model.RegisterLoginViewModel

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

        // レイアウト側の変数との紐づけ
        binding?.apply {
            viewModel = sharedViewModel
            loginFragment = this@LoginFragment
        }
    }

    // login ボタンのヘルパーメソッド
    fun onClickLoginButton() {
        Log.d(TAG, "clicked a login button")
        /**
         * login check method implemented in RegisterLoginViewModel
         * if(user is ok) -> main activity
         */
    }

    // setup画面への遷移を行うヘルパーメソッド
    fun goToSetup() {
        Log.d(TAG, "clicked a text view to set up new account")
        // setup画面に遷移する
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }
    
            
}