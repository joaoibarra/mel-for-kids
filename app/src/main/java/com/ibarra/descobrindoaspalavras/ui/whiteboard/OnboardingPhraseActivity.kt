package com.ibarra.descobrindoaspalavras.ui.whiteboard

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ibarra.descobrindoaspalavras.R
import kotlinx.android.synthetic.main.onboarding_tutorial_whiteboard_activity.*


class OnboardingPhraseActivity: AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_tutorial_whiteboard_activity)
        tvDescription.text = getString(R.string.ready)
        Glide.with(this).load(R.drawable.ic_smart_mel).into(ivMel)

        btnNext.setOnClickListener {
            startOnboardingPen()
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.frase_preparacao_22)
        mediaPlayer?.run { start() }
    }

    fun startOnboardingPen() {
        startActivity(WhiteBoardActivity.start(this, Step.PHRASE))
        finish()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.release()
    }
}