package com.ibarra.descobrindoaspalavras.ui.prewhiteboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibarra.descobrindoaspalavras.R
import kotlinx.android.synthetic.main.pre_whiteboard_step1_activity.*

class PreWhiteBoardStep1Activity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pre_whiteboard_step1_activity)

        btnNext.setOnClickListener {
            val intent = Intent(this, PreWhiteBoardStep2Activity::class.java)
            startActivity(intent)
            finish()
        }

    }
}