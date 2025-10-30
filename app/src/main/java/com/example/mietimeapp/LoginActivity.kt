package com.example.mietimeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
// Import yang diperlukan untuk TextInputEditText
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        val loginButton: Button = findViewById(R.id.btn_login_submit)
        val usernameEditText: TextInputEditText = findViewById(R.id.et_username)
        val passwordEditText: TextInputEditText = findViewById(R.id.et_password)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username dan Password tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("EXTRA_NAME", username)
                startActivity(intent)
                finishAffinity()
            }
        }
        val tvRegisterNow: TextView = findViewById(R.id.tv_register_now)
        tvRegisterNow.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}

