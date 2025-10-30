package com.example.mietimeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        val tvLoginNow: TextView = findViewById(R.id.tv_login_now)

        tvLoginNow.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        val etFullName: EditText = findViewById(R.id.et_full_name)
        val etUsername: EditText = findViewById(R.id.et_username_reg)
        val etPassword: EditText = findViewById(R.id.et_password_reg)
        val btnRegister: Button = findViewById(R.id.btn_register_submit)

        btnRegister.setOnClickListener {
            val fullName = etFullName.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            if (fullName.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
            } else {
                // TODO: Ganti bagian ini dengan logika registrasi Anda yang sebenarnya
                Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()
                val loginIntent = Intent(this, LoginActivity::class.java)
                startActivity(loginIntent)
                finish()
            }
        }
    }
}

