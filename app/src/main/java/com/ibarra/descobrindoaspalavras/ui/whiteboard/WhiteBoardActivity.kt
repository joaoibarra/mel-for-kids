package com.ibarra.descobrindoaspalavras.ui.whiteboard

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.ibarra.descobrindoaspalavras.R
import com.ibarra.descobrindoaspalavras.ui.congratulations.CongratulationsActivity
import kotlinx.android.synthetic.main.white_board_activity.*
import kotlinx.android.synthetic.main.white_board_activity.btnNext
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException




class WhiteBoardActivity : AppCompatActivity() {

    var objAnim: ObjectAnimator? = null

    private var mediaPlayer: MediaPlayer? = null

    var currentProgress = 0

    var step: Step? = null

    companion object {
        private const val STEP = "step"
        fun start(context: Context, step: Step): Intent {
            val intent = Intent(context, WhiteBoardActivity::class.java)
            intent.putExtra(STEP, step)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.white_board_activity)

        step = intent.extras?.get(STEP) as Step?

        when(step) {
            Step.FIRSTWORD -> {
                pulseAnimation()
                overlay.visibility = View.VISIBLE
                mediaPlayer = MediaPlayer.create(this, R.raw.arraste_14)
                mediaPlayer?.run { start() }

                mediaPlayer?.setOnCompletionListener {
                    mediaPlayer?.run { release() }
                    if(overlay.visibility == View.VISIBLE) {
                        stopPulseAnimation()
                        overlay.visibility = View.GONE
                    }

                    if(step == Step.FIRSTWORD) {
                        mediaPlayer?.release()
                        mediaPlayer = MediaPlayer.create(this@WhiteBoardActivity, R.raw.apontador_13)
                        mediaPlayer?.run { start() }
                    }
                }
            }
            Step.SECONDWORD -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.cadernos_16)
                mediaPlayer?.run { start() }
            }
            Step.THIRDWORD -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.regua_18)
                mediaPlayer?.run { start() }
            }
            Step.FOURTHWORD -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.giz_20)
                mediaPlayer?.run { start() }
            }
            Step.PHRASE -> {
                mediaPlayer = MediaPlayer.create(this, R.raw.frase_23)
                mediaPlayer?.run { start() }
            }
        }

        audio.setOnClickListener {
            when(step) {
                Step.FIRSTWORD -> {
                    mediaPlayer?.release()
                    mediaPlayer = MediaPlayer.create(this, R.raw.apontador_13)
                    mediaPlayer?.run { start() }
                }
                Step.SECONDWORD -> {
                    mediaPlayer?.release()
                    mediaPlayer = MediaPlayer.create(this, R.raw.cadernos_16)
                    mediaPlayer?.run { start() }
                }
                Step.THIRDWORD -> {
                    mediaPlayer?.release()
                    mediaPlayer = MediaPlayer.create(this, R.raw.regua_18)
                    mediaPlayer?.run { start() }
                }
                Step.FOURTHWORD -> {
                    mediaPlayer?.release()
                    mediaPlayer = MediaPlayer.create(this, R.raw.giz_20)
                    mediaPlayer?.run { start() }
                }
                Step.PHRASE -> {
                    mediaPlayer?.release()
                    mediaPlayer = MediaPlayer.create(this, R.raw.frase_23)
                    mediaPlayer?.run { start() }
                }
            }
        }

        btnNext.setOnClickListener {
            when(step) {
                Step.FIRSTWORD -> {
                    saveImageToGallery(whiteBoardCanvas.getBitmap())
                    startActivity(CongratulationsActivity.start(this, Step.FIRSTWORD))
                    finish()
                }
                Step.SECONDWORD -> {
                    saveImageToGallery(whiteBoardCanvas.getBitmap())
                    startActivity(CongratulationsActivity.start(this, Step.SECONDWORD))
                    finish()
                }
                Step.THIRDWORD -> {
                    saveImageToGallery(whiteBoardCanvas.getBitmap())
                    startActivity(CongratulationsActivity.start(this, Step.THIRDWORD))
                    finish()
                }
                Step.FOURTHWORD -> {
                    saveImageToGallery(whiteBoardCanvas.getBitmap())
                    startActivity(CongratulationsActivity.start(this, Step.FOURTHWORD))
                    finish()
                }
                Step.PHRASE -> {
                    saveImageToGallery(whiteBoardCanvas.getBitmap())
                    startActivity(CongratulationsActivity.start(this, Step.PHRASE))
                    finish()
                }
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val metrics = resources.displayMetrics
                val widthCanvas = metrics.widthPixels
                val heightCanvas = metrics.heightPixels

                horizontalScrollview.scrollTo((widthCanvas/100) * p1,0)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                currentProgress = p0?.progress?:0
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
    }

    private fun saveImage(bitmap: Bitmap) {
        var outStream: FileOutputStream? = null
        // Write to SD Card
        try {
            val fileName = String.format("%s_%d.jpg", "Image", System.currentTimeMillis())
            outStream = openFileOutput(fileName, Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outStream)

        } catch (e: FileNotFoundException) {
            Log.d("Save Bitmap", "file not found")
            e.printStackTrace();
        } catch (e: IOException) {
            Log.d("Save Bitmap", "io exception")
            e.printStackTrace()
        } finally {
            outStream?.close()
        }
    }

    private fun saveImageToGallery(finalBitmap: Bitmap) {

        val root = Environment.getExternalStorageDirectory().toString()
        val myDir = File(root)
        myDir.mkdirs()
        val fileName = String.format("%s_%d.jpg", "Image", System.currentTimeMillis())
        val file = File(myDir, fileName)
        if (file.exists()) file.delete()
        Log.i("LOAD", root + fileName)
        try {
            val out = FileOutputStream(file)
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.release()
    }

    private fun pulseAnimation() {
        objAnim = ObjectAnimator.ofPropertyValuesHolder(
            seekBar,
            PropertyValuesHolder.ofFloat("scaleX", 1.2f),
            PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        )
        objAnim?.duration = 500
        objAnim?.repeatCount = ObjectAnimator.INFINITE
        objAnim?.repeatMode = ObjectAnimator.REVERSE
        objAnim?.start()
    }

    private fun stopPulseAnimation() {
        objAnim?.cancel();
    }

}