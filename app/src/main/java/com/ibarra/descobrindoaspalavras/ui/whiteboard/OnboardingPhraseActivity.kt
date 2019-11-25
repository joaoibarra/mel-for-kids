package com.ibarra.descobrindoaspalavras.ui.whiteboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ibarra.descobrindoaspalavras.R
import kotlinx.android.synthetic.main.onboarding_tutorial_whiteboard_activity.*


class OnboardingPhraseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_tutorial_whiteboard_activity)
        tvDescription.text = getString(R.string.ready)
        Glide.with(this).load(R.drawable.ic_smart_mel).into(ivMel)

        btnNext.setOnClickListener {
            startOnboardingPen()
        }
    }

    fun startOnboardingPen() {
        startActivity(WhiteBoardActivity.start(this, Step.PHRASE))
        finish()
    }
}