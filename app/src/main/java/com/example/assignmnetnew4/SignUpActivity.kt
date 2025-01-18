package com.example.assignmnetnew4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import kotlin.coroutines.EmptyCoroutineContext.get

class SignUpActivity : AppCompatActivity() {

    private val credentialsManager = CredentialsManager.getInstance()
    private val fullNameInput: TextInputLayout
        get() = findViewById(R.id.FullNameInput)
    private val fullName: String
        get() = fullNameInput.editText?.text?.toString().orEmpty()

    private val emailInput: TextInputLayout
        get() = findViewById(R.id.ValidEmailInput)

    private val email: String
        get() = emailInput.editText?.text?.toString().orEmpty()

    private val phoneNumberInput: TextInputLayout
        get() = findViewById(R.id.PhoneNumberInput)

    private val phoneNumber: String
        get() = phoneNumberInput.editText?.text?.toString().orEmpty()

    private val passwordInput: TextInputLayout
        get() = findViewById(R.id.StrongPasswordInput)

    private val password: String
        get() = passwordInput.editText?.text?.toString().orEmpty()
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
        val registerButton = findViewById<Button>(R.id.nextButton2)
        registerButton.setOnClickListener {

            if (credentialsManager.isEmailValid(email) && credentialsManager.isPasswordValid(password)) {
                val isRegistered = credentialsManager.register(fullName, email, phoneNumber, password)

                if (isRegistered) {
                    Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                    val loginIntent = Intent(this@SignUpActivity, MainActivity::class.java)
                    startActivity(loginIntent)
                    finish()
                } else {
                    Toast.makeText(this, "Email already taken.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid email or password.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    }



