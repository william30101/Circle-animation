package com.draw.my.drawtest

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.draw.my.drawtest.StyleKitName.Companion.length
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info







open class MyView(context: Context, attrs: AttributeSet?): View(context, attrs), AnkoLogger, ValueAnimator.AnimatorUpdateListener {

    var testPaint = Paint()
    var drawX = 0f
    var drawY= 0f
    var drawRadius = 0f
    var animator: ValueAnimator

    init {
        StyleKitName.drawCanvas1()

        val fillColor = Color.argb(255, 191, 0, 0)

        testPaint.reset()
        testPaint.flags = Paint.ANTI_ALIAS_FLAG
        testPaint.style = Paint.Style.STROKE
        testPaint.strokeWidth = 3f
        testPaint.color = fillColor

        val measure = PathMeasure(StyleKitName.myPath, false)
        length = measure.length

        animator = ValueAnimator.ofFloat(0f, 50f)
        animator.duration = 1000
        animator.addUpdateListener(this)

    }

    override fun onAnimationUpdate(animation: ValueAnimator?) {
        drawRadius = animation?.animatedValue as Float
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        info{ "get event : $event"}
        drawX = event?.x!!
        drawY = event.y
        startAnimating()

        return false
    }

    fun startAnimating() {

        animator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


//        canvas?.drawPath(StyleKitName.myPath, testPaint)
        canvas?.drawCircle(drawX, drawY, drawRadius, testPaint)

    }

    //is called by animtor object
    fun setPhase(phase: Float) {
        Log.d("william", "setPhase called with:" + phase.toString())
        testPaint.pathEffect = createPathEffect(StyleKitName.length, phase, 0.0f)

        invalidate()
    }


    private fun createPathEffect(pathLength: Float, phase: Float, offset: Float): PathEffect {
//        return DashPathEffect(floatArrayOf(pathLength, pathLength),
//                Math.max(phase * pathLength, offset))
        Log.d("william", "effect = ${phase * pathLength} + pathLength = $pathLength" )
        return DashPathEffect(floatArrayOf(phase * pathLength, pathLength), 0f)
    }


    // Resizing Behavior
    fun resizingBehaviorApply(behavior: StyleKitName.ResizingBehavior, rect: RectF, target: RectF?, result: RectF) {
        if (rect == target || target == null) {
            result.set(rect)
            return
        }

        if (behavior == StyleKitName.ResizingBehavior.Stretch) {
            result.set(target)
            return
        }

        val xRatio = Math.abs(target.width() / rect.width())
        val yRatio = Math.abs(target.height() / rect.height())
        var scale = 0f

        when (behavior) {
            StyleKitName.ResizingBehavior.AspectFit -> {
                scale = Math.min(xRatio, yRatio)
            }
            StyleKitName.ResizingBehavior.AspectFill -> {
                scale = Math.max(xRatio, yRatio)
            }
            StyleKitName.ResizingBehavior.Center -> {
                scale = 1f
            }
        }

        val newWidth = Math.abs(rect.width() * scale)
        val newHeight = Math.abs(rect.height() * scale)
        result.set(target.centerX() - newWidth / 2,
                target.centerY() - newHeight / 2,
                target.centerX() + newWidth / 2,
                target.centerY() + newHeight / 2)
    }


}