package com.ibarra.descobrindoaspalavras.ui.tutorialboard

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ibarra.descobrindoaspalavras.R
import com.ibarra.descobrindoaspalavras.ui.prewhiteboard.PreWhiteBoardStep1Activity
import com.ibarra.descobrindoaspalavras.ui.rotatescreen.RotateScreenActivity
import kotlinx.android.synthetic.main.tutorial_board_activity.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class TutorialBoardActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    var polygon: Polygon? = null

    var currentProgress = 0

    companion object {
        private const val POLYGON = "polygon"
        fun start(context: Context, polygon: Polygon): Intent {
            val intent = Intent(context, TutorialBoardActivity::class.java)
            intent.putExtra(POLYGON, polygon)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tutorial_board_activity)

        polygon = intent.extras?.get(POLYGON) as Polygon?

        when(polygon) {
            Polygon.RECTANGLE -> {
                Glide.with(this).load(R.drawable.ic_dotted_rectangle).into(ivPolygonToDraw)
                mediaPlayer = MediaPlayer.create(this, R.raw.quadrado_8)
                mediaPlayer?.run { start() }
            }
            Polygon.CIRCLE -> {
                Glide.with(this).load(R.drawable.ic_circle_stroke).into(ivPolygonToDraw)
                mediaPlayer = MediaPlayer.create(this, R.raw.circulo_9)
                mediaPlayer?.run { start() }
            }
            Polygon.HEXAGON -> {
                Glide.with(this).load(R.drawable.ic_hexagon_stroke).into(ivPolygonToDraw)
                mediaPlayer = MediaPlayer.create(this, R.raw.hexagono_10)
                mediaPlayer?.run { start() }
            }
        }

        audio.setOnClickListener {
            when(polygon) {
                Polygon.RECTANGLE -> {
                    mediaPlayer?.release()
                    mediaPlayer = MediaPlayer.create(this, R.raw.quadrado_8)
                    mediaPlayer?.run { start() }
                }
                Polygon.CIRCLE -> {
                    mediaPlayer?.release()
                    mediaPlayer = MediaPlayer.create(this, R.raw.circulo_9)
                    mediaPlayer?.run { start() }
                }
                Polygon.HEXAGON -> {
                    mediaPlayer?.release()
                    mediaPlayer = MediaPlayer.create(this, R.raw.hexagono_10)
                    mediaPlayer?.run { start() }
                }
            }
        }

        btnNext.setOnClickListener {
            when(polygon) {
                Polygon.RECTANGLE -> {
                    saveImageToGallery(whiteBoardCanvas.getBitmap())
                    startActivity(start(this, Polygon.CIRCLE))
                    finish()
                }
                Polygon.CIRCLE -> {
                    saveImageToGallery(whiteBoardCanvas.getBitmap())
                    startActivity(start(this, Polygon.HEXAGON))
                    finish()
                }
                Polygon.HEXAGON -> {
                    saveImageToGallery(whiteBoardCanvas.getBitmap())
                    val intent = Intent(this, PreWhiteBoardStep1Activity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
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

}