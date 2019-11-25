package com.ibarra.descobrindoaspalavras.ui.tutorialboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibarra.descobrindoaspalavras.R
import kotlinx.android.synthetic.main.onboarding_tutorial_whiteboard_activity.*


class OnboardingTutorialWhiteboardActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_tutorial_whiteboard_activity)

        btnNext.setOnClickListener {
            startOnboardingPen()
        }
    }

    fun startOnboardingPen() {
        val intent = Intent(this, OnboardingPenActivity::class.java)
        startActivity(intent)
        finish()
    }
}