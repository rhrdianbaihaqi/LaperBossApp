package com.example.mietimeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SuccessActivity : AppCompatActivity() {

    private var currentName: String = "Guest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        supportActionBar?.hide()

        currentName = intent.getStringExtra("EXTRA_NAME") ?: "Guest"

        val tvThankYouMessage = findViewById<TextView>(R.id.tv_thank_you_message)
        tvThankYouMessage.text = "Terima kasih, $currentName! Pesananmu sedang disiapkan dan akan segera diantar."

        val kirimButton = findViewById<Button>(R.id.btn_kirim_success)
        kirimButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra("EXTRA_NAME", currentName)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
            finishAffinity()
        }
    }

    override fun onBackPressed() {
    }
}
