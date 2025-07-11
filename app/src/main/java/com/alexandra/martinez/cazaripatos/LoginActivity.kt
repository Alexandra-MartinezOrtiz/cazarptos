package com.alexandra.martinez.cazaripatos

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var fileHandler: FileHandler
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var newUserButton: Button
    private lateinit var rememberMeCheckBox: CheckBox
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initializeUI()
        fileHandler = ExternalFileHelper(this)

        readPreferences()

        loginButton.setOnClickListener {
            if (!validateInput()) return@setOnClickListener

            if (rememberMeCheckBox.isChecked) savePreferences()
            else clearPreferences()

            val email = emailInput.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(EXTRA_LOGIN, email)
            startActivity(intent)
            finish()
        }

        newUserButton.setOnClickListener {
            // Lógica para nuevo usuario aquí
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.title_screen)
        mediaPlayer.start()
    }

    private fun initializeUI() {
        emailInput = findViewById(R.id.editTextEmail)
        passwordInput = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)
        newUserButton = findViewById(R.id.buttonNewUser)
        rememberMeCheckBox = findViewById(R.id.checkBoxRecordarme)
    }

    private fun validateInput(): Boolean {
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()

        if (email.isBlank()) {
            emailInput.error = getString(R.string.error_email_required)
            emailInput.requestFocus()
            return false
        }

        if (password.isBlank()) {
            passwordInput.error = getString(R.string.error_password_required)
            passwordInput.requestFocus()
            return false
        }

        if (password.length < 3) {
            passwordInput.error = getString(R.string.error_password_min_length)
            passwordInput.requestFocus()
            return false
        }

        return true
    }

    private fun readPreferences() {
        val (email, password) = fileHandler.ReadInformation()
        emailInput.setText(email)
        passwordInput.setText(password)
        rememberMeCheckBox.isChecked = email.isNotEmpty()
    }

    private fun savePreferences() {
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()
        fileHandler.SaveInformation(email to password)
    }

    private fun clearPreferences() {
        fileHandler.SaveInformation("" to "")
    }

    override fun onDestroy() {
        mediaPlayer.release()
        super.onDestroy()
    }

    companion object {
        const val EXTRA_LOGIN = "extra_login"
    }
}