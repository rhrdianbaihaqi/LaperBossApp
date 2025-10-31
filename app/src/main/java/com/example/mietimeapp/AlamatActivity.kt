package com.example.mietimeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText

class AlamatActivity : AppCompatActivity() {

    private var currentName: String = "Guest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alamat)

        supportActionBar?.hide()

        currentName = intent.getStringExtra("EXTRA_NAME") ?: "Guest"

        val namaEditText = findViewById<TextInputEditText>(R.id.et_full_name_alamat)

        if (currentName != "Guest") {
            namaEditText.setText(currentName)
        }

        val backButton = findViewById<ImageView>(R.id.iv_back_button)

        backButton.setOnClickListener {
            finish()
        }

        setupPaymentMethodLogic()

        val orderButton = findViewById<Button>(R.id.btn_order_kirim_submit)

        orderButton.setOnClickListener {
            val nama = namaEditText.text.toString()
            val alamat = findViewById<TextInputEditText>(R.id.et_alamat).text.toString()

            if (nama.isEmpty() || alamat.isEmpty()) {
                Toast.makeText(this, "Nama dan Alamat Lengkap harus diisi", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SuccessActivity::class.java)
                intent.putExtra("EXTRA_NAME", currentName)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setupPaymentMethodLogic() {
        val rbCash = findViewById<RadioButton>(R.id.rb_cash)
        val rbTransfer = findViewById<RadioButton>(R.id.rb_transfer)
        val rbEwallet = findViewById<RadioButton>(R.id.rb_ewallet)

        val containerCash = findViewById<ConstraintLayout>(R.id.payment_cash_container)
        val containerTransfer = findViewById<ConstraintLayout>(R.id.payment_transfer_container)
        val containerEwallet = findViewById<ConstraintLayout>(R.id.payment_ewallet_container)

        val allRadioButtons = listOf(rbCash, rbTransfer, rbEwallet)

        fun selectPayment(selected: RadioButton) {
            allRadioButtons.forEach { radioButton ->
                radioButton.isChecked = (radioButton == selected)
            }
        }

        containerCash.setOnClickListener { selectPayment(rbCash) }
        containerTransfer.setOnClickListener { selectPayment(rbTransfer) }
        containerEwallet.setOnClickListener { selectPayment(rbEwallet) }

        rbCash.setOnClickListener { selectPayment(rbCash) }
        rbTransfer.setOnClickListener { selectPayment(rbTransfer) }
        rbEwallet.setOnClickListener { selectPayment(rbEwallet) }

        rbCash.isChecked = true
    }
}
