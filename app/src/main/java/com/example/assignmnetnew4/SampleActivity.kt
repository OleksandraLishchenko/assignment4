package com.example.assignmnetnew4

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class SampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sample)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<View>(R.id.switch_button).setOnClickListener{

            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)

            supportFragmentManager.commit {

                if(currentFragment is FragmentA)
                {
                    replace<FragmentB>(R.id.fragment_container_view)
                    addToBackStack(null)
                }else
                {
                    supportFragmentManager.popBackStack()
                }
            }
        }
    }
}