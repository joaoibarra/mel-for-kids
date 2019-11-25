package com.ibarra.descobrindoaspalavras.ui.whiteboard

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.ibarra.descobrindoaspalavras.R
import com.ibarra.descobrindoaspalavras.ui.congratulations.CongratulationsActivity
import kotlinx.android.synthetic.main.white_board_activity.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class WhiteBoardActivity : AppCompatActivity() {

    var currentProgress = 0

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

        val step = intent.extras?.get(STEP) as Step?

        when(step) {
            Step.FIRSTWORD -> {
            }
            Step.SECONDWORD -> {
            }
            Step.THIRDWORD -> {
            }
            Step.FOURTHWORD -> {
            }
            Step.PHRASE -> {
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

}