package com.ibarra.descobrindoaspalavras.ui.tutorialboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibarra.descobrindoaspalavras.R
import kotlinx.android.synthetic.main.onboarding_pen_activity.*


class OnboardingPenActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_pen_activity)

        btnNext.setOnClickListener {
            startTutorialBoard()
        }
    }

    fun startTutorialBoard() {
        startActivity(TutorialBoardActivity.start(this, Polygon.RECTANGLE))
        finish()
    }
}