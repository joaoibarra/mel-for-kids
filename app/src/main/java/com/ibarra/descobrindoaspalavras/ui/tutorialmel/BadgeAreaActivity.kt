package com.ibarra.descobrindoaspalavras.ui.tutorialmel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibarra.descobrindoaspalavras.R
import kotlinx.android.synthetic.main.badge_area_activity.*

class BadgeAreaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.badge_area_activity)

        btnBack.setOnClickListener {
            finish()
        }
    }
}