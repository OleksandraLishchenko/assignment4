package com.example.assignmnetnew4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_log_in)

        val loginTextView = findViewById<TextView>(R.id.RegisterNowText)
        loginTextView.setOnClickListener {
            Log.d("MainActivity", "Pressed login label")
            val registerNow= Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(registerNow)
            finish()
        }
    }
}


