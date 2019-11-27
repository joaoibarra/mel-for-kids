package com.ibarra.descobrindoaspalavras.ui.congratulations

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibarra.descobrindoaspalavras.R
import kotlinx.android.synthetic.main.congratulations_activity.*

class BadgeUnlockedActivity: AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.badge_unlocked_activity)

        btnNext.setOnClickListener {
            val intent = Intent(this, PrizeUnlockedActivity::class.java)
            startActivity(intent)
           finish()
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.conquista_24)
        mediaPlayer?.run { start() }

    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.release()
    }
}