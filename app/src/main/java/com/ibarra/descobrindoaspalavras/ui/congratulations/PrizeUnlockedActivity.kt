package com.ibarra.descobrindoaspalavras.ui.congratulations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibarra.descobrindoaspalavras.R
import kotlinx.android.synthetic.main.congratulations_activity.*

class PrizeUnlockedActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prize_unlocked_activity)

        btnNext.setOnClickListener {
           finish()
        }

    }
}