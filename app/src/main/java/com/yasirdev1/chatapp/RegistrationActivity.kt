package com.yasirdev1.chatapp

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegistrationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        auth = Firebase.auth




        var CreateAccount = findViewById<TextView>(R.id.CreateAccount)
        var Login = findViewById<TextView>(R.id.Login)
        var Email = findViewById<EditText>(R.id.Email)
        var Password = findViewById<EditText>(R.id.Password)




        CreateAccount.setOnClickListener {
            val  Emailtext = Email.text.toString()
            val EmailRegx = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
            if (Emailtext.isEmpty()){
                Toast.makeText(this, "Please Enter a Email", Toast.LENGTH_SHORT).show()
            }else if (!EmailRegx.matches(Emailtext)) {
                Toast.makeText(this, "Please Enter vaild  Email", Toast.LENGTH_SHORT).show()
            }else{
                auth.createUserWithEmailAndPassword(Emailtext, Password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            val profileInfo = Intent(this,ProfileInfo::class.java)
                            startActivity(profileInfo)

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                task.exception?.message.toString(),
                                Toast.LENGTH_SHORT,
                            ).show()

                        }
                    }
            }
        }

        Login.setOnClickListener {
            val  Emailtext = Email.text.toString()
            val EmailRegx = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
            if (Emailtext.isEmpty()){
                Toast.makeText(this, "Please Enter a Email", Toast.LENGTH_SHORT).show()
            }else if (!EmailRegx.matches(Emailtext)) {
                Toast.makeText(this, "Please Enter vaild  Email", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(Emailtext, Password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val profileInfo = Intent(this,ProfileInfo::class.java)
                            startActivity(profileInfo)

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                task.exception?.message.toString(),
                                Toast.LENGTH_SHORT,
                            ).show()

                        }
                    }
            }
        }
    }



}