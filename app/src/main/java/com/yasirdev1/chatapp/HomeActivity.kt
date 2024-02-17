package com.yasirdev1.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.yasirdev1.chatapp.R

class HomeActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        auth = Firebase.auth



        val currentUser = auth.currentUser
        if (currentUser == null){
            val LoginActivity = Intent(this,RegistrationActivity::class.java)
            startActivity(LoginActivity)
        }

        val listview = findViewById<ListView>(R.id.listView)
        val titles = arrayOf("Title 1", "Title 2", "Title 3")
        val subtitles = arrayOf("Subtitle 1", "Subtitle 2", "Subtitle 3")
        val adapter = CustomAdapter(this, titles, subtitles)
        listview.adapter = adapter

        val more_button = findViewById<ImageButton>(R.id.more_button)

        more_button.setOnClickListener {
            showPopupMenu(it)
        }
    }
    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.popup_menu)

        // Set up menu item click listener
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.settings -> {

                    true
                }
                else -> false
            }
        }

        // Show the popup menu
        popupMenu.show()
    }


}