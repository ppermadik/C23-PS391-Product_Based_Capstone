package com.dicoding.logintest2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textViewUsername: TextView
    private lateinit var textViewFullname: TextView
    private lateinit var textViewEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewUsername = findViewById(R.id.textViewUsername)
        textViewFullname = findViewById(R.id.textViewFullname)
        textViewEmail = findViewById(R.id.textViewEmail)

        val username = intent.getStringExtra("username")
        val fullname = intent.getStringExtra("fullname")
        val email = intent.getStringExtra("email")

        textViewUsername.text = "Username: $username"
        textViewFullname.text = "Fullname: $fullname"
        textViewEmail.text = "Email: $email"
    }
}
