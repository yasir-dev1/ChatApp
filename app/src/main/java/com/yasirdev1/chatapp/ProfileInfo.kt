package com.yasirdev1.chatapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.database.values
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlin.math.log

class ProfileInfo : AppCompatActivity() {

    private  lateinit var AddPhoto : FloatingActionButton
    private lateinit var Photo : Intent

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_info)



        val User = Firebase.auth.currentUser

        val db = Firebase.firestore
        val storgeref = Firebase.storage.reference



        AddPhoto = findViewById(R.id.add_photo)
        val usernameEditText = findViewById<EditText>(R.id.username)
        val NextButton = findViewById<Button>(R.id.Next)


        AddPhoto.setOnClickListener {
            ImagePicker.with(this)
                .cropSquare()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }


        NextButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            if (!username.isEmpty()){
                if (Photo != null){
                    Photo.data?.let { it1 ->
                        val s = storgeref.child("ProfileImages/${User?.uid.toString()}").putFile(
                            it1
                        )
                    }
                }


                val user = hashMapOf(
                    "Email" to User?.email,
                    "Username" to username,
                    "ProfilePhoto" to "Default"
                )
                db.collection("Users").document(User?.uid.toString()).set(user)
                    .addOnSuccessListener { documentReference ->
                        Log.d("result", "DocumentSnapshot added with ID: ${documentReference}")
                    }
                    .addOnFailureListener { e ->
                        Log.w("result", "Error adding document", e)
                    }


            }

        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageView = findViewById<ImageView>(R.id.profile)
        imageView.setImageURI(data?.data)
        if (data != null) {
            Photo = data
        }


    }
}