package com.example.assignmnetnew4

import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class FragmentSampleActivity :
    AppCompatActivity(R.layout.activity_fragment_sample),
    LoginFragment.EventInterface, RegisterFragment.EventInterface {

    var credentialsManager = CredentialsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                val Fragment = LoginFragment().apply {
                    credentialsManager = this@FragmentSampleActivity.credentialsManager
                }
                replace(R.id.fragment_container_view, Fragment)
            }
        }

    }

    override fun onRegisterPressed() {
        Log.d("JoinActivity", "Switching to RegisterFragment")
        supportFragmentManager.commit {
            replace<RegisterFragment>(R.id.fragment_container_view).apply {
                credentialsManager = this@FragmentSampleActivity.credentialsManager
            }
            addToBackStack(null)
        }
    }

    override fun onLoginPressed() {
        Log.d("JoinActivity", "Switching to LoginFragment")
        supportFragmentManager.commit {
            replace<LoginFragment>(R.id.fragment_container_view).apply {
                credentialsManager = this@FragmentSampleActivity.credentialsManager
            }
            addToBackStack(null)
        }
    }
}

