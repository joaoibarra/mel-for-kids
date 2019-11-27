package com.ibarra.descobrindoaspalavras.ui.rotatescreen

import android.content.Intent
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.OrientationEventListener
import androidx.appcompat.app.AppCompatActivity
import com.ibarra.descobrindoaspalavras.R
import com.ibarra.descobrindoaspalavras.ui.tutorialboard.OnboardingTutorialWhiteboardActivity
import com.ibarra.descobrindoaspalavras.ui.tutorialmel.MelActivitiesActivity
import com.ibarra.descobrindoaspalavras.ui.tutorialmel.MelAreaActivity
import kotlinx.android.synthetic.main.rotate_screen_activity.*


class RotateScreenActivity: AppCompatActivity() {

    private var isLandscape = false

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rotate_screen_activity)

        addOrientationListener()

        mediaPlayer = MediaPlayer.create(this, R.raw.vire_a_tela_3)
        mediaPlayer?.run { start() }
    }

    private fun addOrientationListener() {
        val listener =
            object : OrientationEventListener(this, SensorManager.SENSOR_DELAY_UI) {
                override fun onOrientationChanged(orientation: Int) {

                    if ((orientation in 230..290 || orientation in 70..90) && !isLandscape) {
                        isLandscape = true
                        tvDescription.text = getString(R.string.loading)
                        startMelTutorialBoard()
                    }
//                    } else if (orientation == -1) {
//                        //KEEP THE CURRENT STATE
//                    } else {
//                        isLandscape = false
//                    }
                }
            }
        if (listener.canDetectOrientation())
            listener.enable()
    }

    fun startMelTutorialBoard() {
        mediaPlayer?.release()
        val intent = Intent(this, MelAreaActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun startTutorialBoard() {
        val intent = Intent(this, OnboardingTutorialWhiteboardActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.release()
    }
}