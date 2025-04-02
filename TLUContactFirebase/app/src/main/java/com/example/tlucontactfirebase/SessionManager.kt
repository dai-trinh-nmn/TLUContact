package com.example.tlucontactfirebase

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val USER_EMAIL = "user_email"
        private const val IS_LOGGED_IN = "is_logged_in"
    }

    fun saveEmail(email: String) {
        prefs.edit().putString(USER_EMAIL, email).apply()
    }

    fun getEmail(): String? {
        return prefs.getString(USER_EMAIL, null)
    }

    fun saveLoginStatus(isLoggedIn: Boolean) {
        prefs.edit().putBoolean(IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(IS_LOGGED_IN, false)
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}