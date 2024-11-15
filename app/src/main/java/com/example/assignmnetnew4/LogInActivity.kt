package com.example.assignmnetnew4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_log_in)

        findViewById<TextView>(R.id.RegisterNowText)?.setOnClickListener {
            setContentView(R.layout.account_sign_up)

            findViewById<TextView>(R.id.LogInText)?.setOnClickListener {
                setContentView(R.layout.account_log_in)
            }
        }
    }
}
