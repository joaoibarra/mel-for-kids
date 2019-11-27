package com.ibarra.descobrindoaspalavras.ui.tutorialboard

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibarra.descobrindoaspalavras.R
import kotlinx.android.synthetic.main.onboarding_pen_activity.*


class OnboardingPenActivity: AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding_pen_activity)

        btnNext.setOnClickListener {
            startTutorialBoard()
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.caneta_7)
        mediaPlayer?.run { start() }
    }

    fun startTutorialBoard() {
        startActivity(TutorialBoardActivity.start(this, Polygon.RECTANGLE))
        finish()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.release()
    }
}