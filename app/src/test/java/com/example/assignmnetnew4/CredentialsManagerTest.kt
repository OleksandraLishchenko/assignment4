package com.example.assignmnetnew4

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
class CredentialsManagerTest {
    // Test empty email
    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val isEmailValid = credentialsManager.isEmailValid("")
        assertEquals(false, isEmailValid)
    }
    // Test proper email format
    @Test
    fun givenProperEmail_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        assertEquals(true, credentialsManager.isEmailValid("email@example.com"))
    }
    // Test wrong email
    @Test
    fun givenWrongEmailFormat_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        assertEquals(false, credentialsManager.isEmailValid("email@.st"))
    }
    // Test empty password
    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        assertEquals(false, credentialsManager.isPasswordValid(""))
    }
    // Test filled password
    @Test
    fun givenValidPassword_thenReturnTrue() {
        val credentialsManager = CredentialsManager()
        assertEquals(true, credentialsManager.isPasswordValid("P@ssword1"))
    }

    @Test  fun givenProperUnusedCredentials()
    {
        val credentialsManager=CredentialsManager()
        val newEmail = "another@te.st"
        val newPassword= "1234qwer"
        credentialsManager.register("Full name", newEmail,"600 600 600" , newPassword)
        val isloginSuccess = credentialsManager.login(newEmail, newPassword)
        assertTrue(isloginSuccess)

    }

    @Test
    fun givenTakenEmail_thenRegisterFails() {
        val credentialsManager = CredentialsManager()
        val email = "another@te.st"
        val password = "1234qwer"

        credentialsManager.register("Full name", email, "600 600 600", password)
        val isRegisterSuccess = credentialsManager.register("Full name", email, "600 600 600", password)

        assertEquals(false, isRegisterSuccess)
    }

    @Test
    fun givenTakenEmailDiffCaps_thenRegisterFails() {
        val credentialsManager = CredentialsManager()
        val emailOriginal = "another@te.st"
        val emailDiffCaps = "Another@Te.sT"
        val password = "1234qwer"

        credentialsManager.register("Full name", emailOriginal, "600 600 600", password)
        val isRegisterSuccess = credentialsManager.register("Full name", emailDiffCaps, "600 600 600", password)

        assertEquals(false, isRegisterSuccess)
    }

    @Test
    fun givenCorrectCredentialsWithDiffCaps_thenLoginSucceeds() {
        val credentialsManager = CredentialsManager()
        val emailOriginal = "another@te.st"
        val emailDiffCaps = "Another@Te.sT"
        val password = "1234qwer"

        credentialsManager.register("Full name", emailOriginal, "600 600 600", password)
        val isLoginSuccess = credentialsManager.login(emailDiffCaps, password)

        assertTrue(isLoginSuccess)
    }
}