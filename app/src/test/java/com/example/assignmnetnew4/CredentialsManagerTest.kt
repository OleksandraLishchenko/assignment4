package com.example.assignmnetnew4
import org.junit.Assert.assertEquals
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
}