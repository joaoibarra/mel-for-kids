package com.ibarra.descobrindoaspalavras.ui.congratulations

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibarra.descobrindoaspalavras.R
import kotlinx.android.synthetic.main.congratulations_activity.*

class PrizeUnlockedActivity: AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prize_unlocked_activity)

        btnNext.setOnClickListener {
           finish()
        }


        mediaPlayer = MediaPlayer.create(this, R.raw.premio_25)
        mediaPlayer?.run { start() }

    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.release()
    }

    override fun onBackPressed() {

    }
}