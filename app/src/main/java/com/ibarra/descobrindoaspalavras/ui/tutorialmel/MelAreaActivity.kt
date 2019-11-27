package com.ibarra.descobrindoaspalavras.ui.tutorialmel

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ibarra.descobrindoaspalavras.R
import kotlinx.android.synthetic.main.mel_area_activity.*


class MelAreaActivity: AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    var objAnim: ObjectAnimator? = null

    var isLastAudio = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mel_area_activity)

        pulseAnimation()

        btnNext.setOnClickListener {
            stopPulseAnimation()
            overlay.visibility = View.GONE
            btnNext2.visibility = View.VISIBLE
            val intent = Intent(this, MelActivitiesActivity::class.java)
            startActivity(intent)
        }

        btnNext2.setOnClickListener {
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

        mediaPlayer = MediaPlayer.create(this, R.raw.desafios_4)
        mediaPlayer?.run { start() }

//        mediaPlayer?.setOnCompletionListener(OnCompletionListener { mPlayer ->
//            mediaPlayer?.run {release()}
//            if(!isLastAudio) {
//                startNextAudio()
//            }
//            isLastAudio = true
//
//        })



    }


    private fun pulseAnimation() {
        objAnim = ObjectAnimator.ofPropertyValuesHolder(
            btnNext,
            PropertyValuesHolder.ofFloat("scaleX", 1.5f),
            PropertyValuesHolder.ofFloat("scaleY", 1.5f)
        )
        objAnim?.duration = 300
        objAnim?.repeatCount = ObjectAnimator.INFINITE
        objAnim?.repeatMode = ObjectAnimator.REVERSE
        objAnim?.start()
    }

    fun startNextAudio() {
        mediaPlayer = MediaPlayer.create(this, R.raw.desafios_4)
        mediaPlayer?.run { start() }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.release()
    }

    private fun stopPulseAnimation() {
        objAnim?.cancel();
    }
}