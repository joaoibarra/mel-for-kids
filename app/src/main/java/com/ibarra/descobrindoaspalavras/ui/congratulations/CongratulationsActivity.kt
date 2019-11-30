package com.ibarra.descobrindoaspalavras.ui.congratulations

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ibarra.descobrindoaspalavras.R
import com.ibarra.descobrindoaspalavras.ui.whiteboard.OnboardingPhraseActivity
import com.ibarra.descobrindoaspalavras.ui.whiteboard.Step
import com.ibarra.descobrindoaspalavras.ui.whiteboard.WhiteBoardActivity
import kotlinx.android.synthetic.main.congratulations_activity.*

class CongratulationsActivity: AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    companion object {
        private const val STEP = "step"
        fun start(context: Context, step: Step): Intent {
            val intent = Intent(context, CongratulationsActivity::class.java)
            intent.putExtra(STEP, step)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.congratulations_activity)
        val step = intent.extras?.get(STEP) as Step?

        when(step) {
            Step.FIRSTWORD -> {
                main.setBackgroundColor(ContextCompat.getColor(this, R.color.lightGreen))
                ivPuzzle.setImageDrawable(getDrawable(R.drawable.ic_first_puzzle))

                mediaPlayer = MediaPlayer.create(this, R.raw.sucesso1_15)
                mediaPlayer?.run { start() }
            }
            Step.SECONDWORD -> {
                main.setBackgroundColor(ContextCompat.getColor(this, R.color.darkPurple))
                ivPuzzle.setImageDrawable(getDrawable(R.drawable.ic_second_puzzle))

                mediaPlayer = MediaPlayer.create(this, R.raw.sucesso2_17)
                mediaPlayer?.run { start() }
            }
            Step.THIRDWORD -> {
                main.setBackgroundColor(ContextCompat.getColor(this, R.color.darkOrange))
                ivPuzzle.setImageDrawable(getDrawable(R.drawable.ic_third_puzzle))

                mediaPlayer = MediaPlayer.create(this, R.raw.sucesso3_19)
                mediaPlayer?.run { start() }
            }
            Step.FOURTHWORD -> {
                main.setBackgroundColor(ContextCompat.getColor(this, R.color.darkBlue))
                ivPuzzle.setImageDrawable(getDrawable(R.drawable.ic_fourth_puzzle))

                mediaPlayer = MediaPlayer.create(this, R.raw.sucesso4_21)
                mediaPlayer?.run { start() }
            }
            Step.PHRASE -> {
                val intent = Intent(this, BadgeUnlockedActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        btnNext.setOnClickListener {
            when (step) {
                Step.FIRSTWORD -> {
                    startActivity(WhiteBoardActivity.start(this, Step.SECONDWORD))
                    finish()
                }
                Step.SECONDWORD -> {
                    startActivity(WhiteBoardActivity.start(this, Step.THIRDWORD))
                    finish()
                }
                Step.THIRDWORD -> {
                    startActivity(WhiteBoardActivity.start(this, Step.FOURTHWORD))
                    finish()
                }
                Step.FOURTHWORD -> {
                    val intent = Intent(this, OnboardingPhraseActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                Step.PHRASE -> {
//                    startActivity(WhiteBoardActivity.start(this, Step.PHRASE))
                    finish()
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.release()
    }

    override fun onBackPressed() {

    }
}