package com.example.assignmnetnew4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_sign_up)

        val loginTextView = findViewById<TextView>(R.id.LogInText)
        loginTextView.setOnClickListener {
            Log.d("MainActivity", "Pressed login label")
            val logInLab= Intent(this@SignUpActivity, MainActivity::class.java)
            startActivity(logInLab)
            finish()
        }
    }
}


