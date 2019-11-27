package com.ibarra.descobrindoaspalavras.ui.prewhiteboard

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ibarra.descobrindoaspalavras.R
import com.ibarra.descobrindoaspalavras.ui.whiteboard.Step
import com.ibarra.descobrindoaspalavras.ui.whiteboard.WhiteBoardActivity
import kotlinx.android.synthetic.main.pre_whiteboard_step1_activity.*

class PreWhiteBoardStep2Activity: AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pre_whiteboard_step2_activity)

        btnNext.setOnClickListener {
            startActivity(WhiteBoardActivity.start(this, Step.FIRSTWORD))
            finish()
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.materiais_12)
        mediaPlayer?.run { start() }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.release()
    }
}