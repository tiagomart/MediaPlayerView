package com.tmrtapps.mediaplayerview

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View

@SuppressLint("ClickableViewAccessibility")
class ButtonAnimationTouchListener : View.OnTouchListener {

    var scaleX = 0.90f
    var scaleY = 0.90f
    var duration: Long = 100

    private var scaleDownAnimationEnded = false
    private var canBack = false

    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {

        if (view != null && motionEvent != null) {

            if (motionEvent.action == MotionEvent.ACTION_DOWN) {

                scaleDownAnimationEnded = false
                canBack = false

                view.animate().cancel()
                view.animate().scaleY(scaleX).scaleX(scaleY).setDuration(duration).withEndAction {

                    scaleDownAnimationEnded = true

                    if (canBack) {
                        view.animate().cancel()
                        view.animate().scaleY(1.0f).scaleX(1.0f).setDuration(duration).start()
                    }

                }.start()

            } else if (motionEvent.action == MotionEvent.ACTION_UP) {

                canBack = true

                if (scaleDownAnimationEnded) {
                    view.animate().cancel()
                    view.animate().scaleY(1.0f).scaleX(1.0f).setDuration(duration).start()
                }
            }

            return false
        }

        return true
    }
}