package com.example.assignmnetnew4

class CredentialsManager {
    val credentials = mutableMapOf<String, String>(
        Pair("test", "1234")
    )
    private var isUserLoggedIn = false

    companion object {
        @Volatile
        private var instance: CredentialsManager? = null

        fun getInstance(): CredentialsManager {
            return instance ?: synchronized(this) {
                instance ?: CredentialsManager().also { instance = it }
            }
        }
    }

    fun isEmailValid(email: String): Boolean {
        val emailPattern = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+")
        val regex = Regex(emailPattern)
        return regex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        val passwordPattern =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"|,.<>/?]).{8,}$"
        return Regex(passwordPattern).matches(password)
    }

    fun login(email: String, password: String): Boolean {
        val editedEmail = email.lowercase()
        val isValid = credentials[editedEmail].equals(password)
        if (isValid) {
            isUserLoggedIn = true
        }
        return isValid
    }

    fun register(fullName: String, email: String, phoneNumber: String, password: String): Boolean {
        val editedEmail = email.lowercase()

        return if (credentials.containsKey(editedEmail)) {
            false
        } else {
            credentials[editedEmail] = password
            true
        }
    }

    fun logout() {
        isUserLoggedIn = false
    }

}
