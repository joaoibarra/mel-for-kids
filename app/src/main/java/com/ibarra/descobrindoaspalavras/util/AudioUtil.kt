package com.ibarra.descobrindoaspalavras.util

import android.app.Activity
import android.speech.tts.TextToSpeech
import android.widget.Toast
import java.util.*

class AudioUtil(private val activity: Activity,
                private val br: Boolean) : TextToSpeech.OnInitListener {

    private val tts: TextToSpeech = TextToSpeech(activity, this)

    override fun onInit(i: Int) {
        if (i == TextToSpeech.SUCCESS) {

            val localeBR = Locale("pt", "BR")
            val localeUS = Locale.US

            val result: Int
            result = if (br) tts.setLanguage(localeBR) else tts.setLanguage(localeUS)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(activity, "This Language is not supported", Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(activity, "Initilization Failed!", Toast.LENGTH_SHORT).show()
        }
    }

    fun speakOut(message: String) {
        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}