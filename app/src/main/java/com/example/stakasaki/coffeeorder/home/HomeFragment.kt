package com.example.stakasaki.coffeeorder.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.stakasaki.coffeeorder.databinding.FragmentHomeBinding
import com.example.stakasaki.coffeeorder.model.User

class HomeFragment : Fragment() {

    companion object {
        var currentUser: User? = null
    }
    private var binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel.fetchCurrentUser()
        homeViewModel.verifyUserIsLoggedIn(this)
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.homeFragment = this
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    
}