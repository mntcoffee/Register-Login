package com.example.stakasaki.coffeeorder.registerlogin

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.stakasaki.coffeeorder.home.MainActivity
import com.example.stakasaki.coffeeorder.model.User
import com.example.stakasaki.coffeeorder.registerlogin.RegisterFragment.Companion.TAG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class RegisterLoginViewModel: ViewModel() {

    private lateinit var context: Context
    private lateinit var username: String

    // a call back method when register button is tapped
    fun performRegister(
        _context: Context,
        email: String,
        password: String,
        _username: String) {

        context = _context
        username = _username
        // if either email or password are null, return
        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context,
                    "Please input both email and password",
                    Toast.LENGTH_SHORT
            ).show()
            return
        }

        Log.d(TAG, "Email is $email")
        Log.d(TAG, "password is $password")

        // register user to firebase authentication by email and password
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(!it.isSuccessful) return@addOnCompleteListener
                    // else if successful
                    Log.d(TAG, "Successfully created user with uid: ${it.result!!.user!!.uid}")
                    // register user to firebase database
                    saveUserToFirebaseDatabase()
                }
                .addOnFailureListener {
                    Log.d(TAG, "Failed to create user: ${it.message}")
                    Toast.makeText(context,
                            "Failed to create ussr:/n${it.message}",
                            Toast.LENGTH_SHORT
                    ).show()
                }
    }

    private fun saveUserToFirebaseDatabase() {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        // user data contains uid and username
        val user = User(uid, username)

        // add user to (users/uid)
        ref.setValue(user)
                .addOnCompleteListener {
                    Log.d(TAG, "Finally we saved the user to Firebase Database")
                    val intent = Intent(context, MainActivity::class.java)
                    // after registration, block going back to register activity again
                    // even though tasks remain, start new task
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                }
                .addOnFailureListener {
                    Log.d("RegisterActivity", "Failed to set value to database: ${it.message}")
                }
    }

    fun performLogin(
        _context: Context,
        email: String,
        password: String
    ) {
        context = _context

        if(email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context,
                    "Please input both email and password",
                    Toast.LENGTH_SHORT
            ).show()
            return
        }
        Log.d("Login", "Attempt login with email/pw: $email/***")

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(!it.isSuccessful) return@addOnCompleteListener
                Log.d("Login", "Successfully login with uid: ${it.result!!.user!!.uid}")

                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }
            .addOnFailureListener {
                Log.d("Login", "Failed to login: ${it.message}")
                Toast.makeText(context, "Failed to login:\n${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

}