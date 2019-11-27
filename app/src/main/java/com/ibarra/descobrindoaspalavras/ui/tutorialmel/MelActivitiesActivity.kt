package com.ibarra.descobrindoaspalavras.ui.tutorialmel

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import com.ibarra.descobrindoaspalavras.R
import com.ibarra.descobrindoaspalavras.ui.prewhiteboard.PreWhiteBoardStep1Activity
import com.ibarra.descobrindoaspalavras.ui.tutorialboard.OnboardingTutorialWhiteboardActivity
import com.ibarra.descobrindoaspalavras.util.AudioUtil
import kotlinx.android.synthetic.main.mel_step1_activity.*
import java.util.*





class MelActivitiesActivity: AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mel_step1_activity)

        melArea.setOnClickListener {
            finish()
        }

        map.setOnClickListener {
            val intent = Intent(this, OnboardingTutorialWhiteboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.novo_desafio_5)
        mediaPlayer?.run { start() }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.release()
    }

//        CoachMarkOverlay.Builder(this)
//            .setOverlayTargetView(melArea)
//            .setInfoViewBuilder(
//                CoachMarkInfo.Builder(this)
//                    .setInfoText("TextString").setMargin(30, 30, 30, 30)
//            )
//                    .setSkipButtonBuilder(
//                        CoachMarkSkipButton.Builder(this)
//                            .setButtonClickListener(object : CoachMarkSkipButton.ButtonClickListener {
//                                override fun onSkipButtonClick(view: View) {
//                                    (window.decorView as ViewGroup).removeView(view)
////                                    coachMarkSequence.clearList()
////                                    button?.setText("Text")
//                                }})).build().show(main)
//
//    }
}