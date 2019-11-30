package com.ibarra.descobrindoaspalavras.ui.prewhiteboard

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibarra.descobrindoaspalavras.R
import kotlinx.android.synthetic.main.pre_whiteboard_step1_activity.*

class PreWhiteBoardStep1Activity: AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pre_whiteboard_step1_activity)

        btnNext.setOnClickListener {
            val intent = Intent(this, PreWhiteBoardStep2Activity::class.java)
            startActivity(intent)
            finish()
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.palavras_11)
        mediaPlayer?.run { start() }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.release()
    }

    override fun onBackPressed() {

    }
}