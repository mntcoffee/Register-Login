package com.example.stakasaki.coffeeorder.home

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.stakasaki.coffeeorder.home.HomeFragment.Companion.currentUser
import com.example.stakasaki.coffeeorder.model.User
import com.example.stakasaki.coffeeorder.registerlogin.RegisterLoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeViewModel : ViewModel() {

    companion object {
        const val TAG = "Home"
    }

    fun fetchCurrentUser() {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                currentUser = snapshot.getValue(User::class.java)
                Log.d(TAG, "Current User: ${currentUser?.username}")
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun verifyUserIsLoggedIn(fragment: HomeFragment) {
        val uid = FirebaseAuth.getInstance().uid
        if(uid == null) {
            val intent = Intent(fragment.context, RegisterLoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            fragment.startActivity(intent)
        }
    }
}