package com.alexandra.martinez.cazaripatos

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class EncryptedPreferencesManager(context: Context) : FileHandler {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val encryptedPrefs = EncryptedSharedPreferences.create(
        context,
        "secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        encryptedPrefs.edit()
            .putString(LOGIN_KEY, datosAGrabar.first)
            .putString(PASSWORD_KEY, datosAGrabar.second)
            .apply()
    }

    override fun ReadInformation(): Pair<String, String> {
        val email = encryptedPrefs.getString(LOGIN_KEY, "") ?: ""
        val password = encryptedPrefs.getString(PASSWORD_KEY, "") ?: ""
        return email to password
    }
}
