// Put your package name here. Check your activity for reference.
package com.example.assignmnetnew4

import android.util.Patterns

class CredentialsManager {
    fun isEmailValid(email: String): Boolean {
        Patterns.EMAIL_ADDRESS
        val emailPattern = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"+
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+
                ")+")
        val regex = Regex(emailPattern)
        return regex.matches(email)

    }
    // Valid password
    fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"|,.<>/?]).{8,}$"
        val regex = Regex(passwordPattern)
        return regex.matches(password)
    }
}
