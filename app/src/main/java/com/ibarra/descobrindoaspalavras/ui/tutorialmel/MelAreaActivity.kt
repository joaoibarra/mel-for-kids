package com.ibarra.descobrindoaspalavras.ui.tutorialmel

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibarra.descobrindoaspalavras.R
import com.ibarra.descobrindoaspalavras.ui.prewhiteboard.PreWhiteBoardStep2Activity
import kotlinx.android.synthetic.main.mel_area_activity.*

class MelAreaActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mel_area_activity)

        btnNext.setOnClickListener {
            val intent = Intent(this, MelActivitiesActivity::class.java)
            startActivity(intent)
        }

        menu3.setOnClickListener {
            val intent = Intent(this, BadgeAreaActivity::class.java)
            startActivity(intent)
        }

        menu4.setOnClickListener {
//            val intent = Intent(this, MelActivitiesActivity::class.java)
//            startActivity(intent)
        }
    }
}