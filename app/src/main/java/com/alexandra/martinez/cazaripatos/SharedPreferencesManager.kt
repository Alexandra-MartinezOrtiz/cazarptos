package com.alexandra.martinez.cazaripatos

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) : FileHandler {

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        sharedPref.edit().apply {
            putString(LOGIN_KEY, datosAGrabar.first)
            putString(PASSWORD_KEY, datosAGrabar.second)
            apply()
        }
    }

    override fun ReadInformation(): Pair<String, String> {
        val email = sharedPref.getString(LOGIN_KEY, "") ?: ""
        val password = sharedPref.getString(PASSWORD_KEY, "") ?: ""
        return email to password
    }
}
