package com.ibarra.descobrindoaspalavras.ui.tutorial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ibarra.descobrindoaspalavras.R
import com.ibarra.descobrindoaspalavras.ui.rotatescreen.RotateScreenActivity

class TutorialActivity : AppCompatActivity() {

    var step = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            setFragment()
        }
    }

    fun setFragment() {
        when(step) {
            0 -> changeFragment(
                R.drawable.ic_tutorial_mel_1,
                getString(R.string.step1_title),
                getString(R.string.step1_description),
                R.raw.oi_1)
            1 -> changeFragment(
                R.drawable.ic_jumping_mel,
                getString(R.string.step2_title),
                getString(R.string.step2_description),
                R.raw.oi_2)
            2 -> {
                val intent = Intent(this, RotateScreenActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun changeFragment(imageId: Int,
                       title: String,
                       description: String,
                       audio: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, TutorialFragment.newInstance(imageId, title, description, audio))
            .commitNow()
        step++
    }

    override fun onBackPressed() {

    }
}
