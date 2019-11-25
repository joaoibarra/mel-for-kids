package com.ibarra.descobrindoaspalavras.ui.whiteboard

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.res.ResourcesCompat
import com.ibarra.descobrindoaspalavras.R





private const val STROKE_WIDTH = 12f

class MyCanvasView@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : View(context, attrs, defStyle) {

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap
    private var path = Path()

//    private var scrollPosX = 0f
//    private var scrollPosY = 0f

    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f

    private var currentX = 0f
    private var currentY = 0f

    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)

    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop

    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        val metrics = context.resources.displayMetrics
        val widthCanvas = metrics.widthPixels*2
        val heightCanvas = metrics.heightPixels


        super.onSizeChanged(widthCanvas, heightCanvas, oldWidth, oldHeight)
        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(widthCanvas, heightCanvas, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
//        extraCanvas.drawColor(backgroundColor)
    }



    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val metrics = context.resources.displayMetrics
        val widthCanvas = metrics.widthPixels*2
        val heightCanvas = metrics.heightPixels
        setMeasuredDimension(widthCanvas, heightCanvas)
    }

//    public override fun onDraw(canvas: Canvas) {
//        extraCanvas = canvas
//    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(extraBitmap, this.left.toFloat(), 0f, null)
    }

//    fun scroll

    private val paint = Paint().apply {
        color = drawColor
        // Smooths out edges of what is drawn without affecting shape.
        isAntiAlias = true
        // Dithering affects how colors with higher-precision than the device are down-sampled.
        isDither = true
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
        strokeWidth = STROKE_WIDTH // default: Hairline-width (really thin)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        getParent().requestDisallowInterceptTouchEvent(true)
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }

//    override fun setScrollX(percentage: Int) {
//        val metrics = context.resources.displayMetrics
//        val widthCanvas = metrics.widthPixels*2
//        val heightCanvas = metrics.heightPixels
//    }

    private fun touchStart() {
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    private fun touchMove() {
        val dx = Math.abs(motionTouchEventX - currentX)
        val dy = Math.abs(motionTouchEventY - currentY)
        if (dx >= touchTolerance || dy >= touchTolerance) {
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
            path.quadTo(currentX, currentY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
            currentX = motionTouchEventX
            currentY = motionTouchEventY
            // Draw the path in the extra bitmap to cache it.
            extraCanvas.drawPath(path, paint)
        }
        invalidate()
    }

    private fun touchUp() {
        // Reset the path so it doesn't get drawn again.
        path.reset()
    }

    fun getBitmap(): Bitmap {
        return extraBitmap
    }
}