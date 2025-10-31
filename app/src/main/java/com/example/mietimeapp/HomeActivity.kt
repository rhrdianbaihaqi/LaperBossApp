package com.example.mietimeapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView

class HomeActivity : AppCompatActivity() {

    private var currentName: String = "Guest"
    private data class MenuItem(
        val card: MaterialCardView,
        val title: String,
        val categoryTitleView: TextView
    )

    private val allMenuItems = mutableListOf<MenuItem>()
    private val categoryTitles = mutableSetOf<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()
        currentName = intent.getStringExtra("EXTRA_NAME") ?: "Guest"
        val haloTextView = findViewById<TextView>(R.id.tv_halo_name)
        haloTextView.text = "Selamat Datang, $currentName"

        setupBottomNavigation()
        setupMenuItems()
        val searchEditText = findViewById<EditText>(R.id.et_search)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                filterMenu(s.toString())
            }
        })
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_home

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_order -> {
                    val intent = Intent(this, OrderActivity::class.java)
                    intent.putExtra("EXTRA_NAME", currentName)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    intent.putExtra("EXTRA_NAME", currentName)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupMenuItems() {
        val tvMakananTitle = findViewById<TextView>(R.id.tv_makanan_title)
        val tvDimsumTitle = findViewById<TextView>(R.id.tv_dimsum_title)
        val tvMinumanTitle = findViewById<TextView>(R.id.tv_minuman_title)
        categoryTitles.add(tvMakananTitle)
        categoryTitles.add(tvDimsumTitle)
        categoryTitles.add(tvMinumanTitle)

        allMenuItems.add(MenuItem(findViewById(R.id.card_makanan_1), "Batagor Bandung", tvMakananTitle))
        allMenuItems.add(MenuItem(findViewById(R.id.card_makanan_2), "Ayam Geprek", tvMakananTitle))
        allMenuItems.add(MenuItem(findViewById(R.id.card_makanan_3), "Mie Ayam Bakso", tvMakananTitle))
        allMenuItems.add(MenuItem(findViewById(R.id.card_makanan_4), "Nasi Goreng Telor Ceplok", tvMakananTitle))

        allMenuItems.add(MenuItem(findViewById(R.id.card_dimsum_1), "Lumpia Ayam", tvDimsumTitle))
        allMenuItems.add(MenuItem(findViewById(R.id.card_dimsum_2), "Tempe Mendoan", tvDimsumTitle))
        allMenuItems.add(MenuItem(findViewById(R.id.card_dimsum_3), "Ceker Pedas", tvDimsumTitle))

        allMenuItems.add(MenuItem(findViewById(R.id.card_minuman_1), "Es Teh Manis", tvMinumanTitle))
        allMenuItems.add(MenuItem(findViewById(R.id.card_minuman_2), "Es Jeruk Peras", tvMinumanTitle))
        allMenuItems.add(MenuItem(findViewById(R.id.card_minuman_3), "Matcha Boba", tvMinumanTitle))
    }

    private fun filterMenu(query: String) {
        val searchQuery = query.lowercase().trim()
        val visibleCountPerCategory = mutableMapOf<TextView, Int>()
        for (titleView in categoryTitles) {
            visibleCountPerCategory[titleView] = 0
        }

        for (item in allMenuItems) {
            val itemTitle = item.title.lowercase()
            val isVisible = itemTitle.contains(searchQuery) || searchQuery.isEmpty()

            if (isVisible) {
                item.card.visibility = View.VISIBLE
                visibleCountPerCategory[item.categoryTitleView] =
                    visibleCountPerCategory.getOrDefault(item.categoryTitleView, 0) + 1
            } else {
                item.card.visibility = View.GONE
            }
        }
        for (titleView in categoryTitles) {
            val showTitle = visibleCountPerCategory.getOrDefault(titleView, 0) > 0 || searchQuery.isEmpty()
            titleView.visibility = if (showTitle) View.VISIBLE else View.GONE
        }
    }
}
