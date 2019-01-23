package com.draw.my.drawtest

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MyConstraintLayout(context: Context, attrs: AttributeSet?): ConstraintLayout(context, attrs), AnkoLogger {

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {

        val action = ev?.action
        info { "onInterceptTouchEvent event "}
        myView.dispatchTouchEvent(ev)

        return super.onInterceptTouchEvent(ev)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        info { "onTouchEvent event "}
        return super.onTouchEvent(event)
    }
}