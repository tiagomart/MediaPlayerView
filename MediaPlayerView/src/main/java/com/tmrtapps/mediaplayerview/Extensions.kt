package com.tmrtapps.mediaplayerview

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView

fun Long.toTimeString(context: Context): String {

    val millis = this
    val hours = (millis / (1000 * 60 * 60)).toInt()
    val minutes = (millis % (1000 * 60 * 60) / (1000 * 60)).toInt()
    val seconds = (millis % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()

    return if (hours > 0) context.getString(R.string.hours_minutes_seconds, hours, minutes, seconds) else context.getString(R.string.minutes_seconds, minutes, seconds)
}

fun View.setAnimatedBackgroundColor(color: Int = Color.GREEN, duration: Long = 250, startDelay: Long = 0) {

    var originalColor = Color.TRANSPARENT
    if (this is CardView) {
        originalColor = cardBackgroundColor.defaultColor
    } else {
        if (background is ColorDrawable) {
            originalColor = (background as ColorDrawable).color
        }
    }

    val valueAnimator = ValueAnimator.ofObject(ArgbEvaluator(), originalColor, color)
    valueAnimator.duration = duration
    valueAnimator.startDelay = startDelay
    valueAnimator.addUpdateListener { animator ->
        if (this is CardView) {
            setCardBackgroundColor(animator.animatedValue as Int)
        } else {
            setBackgroundColor(animator.animatedValue as Int)
        }
    }

    valueAnimator.start()
}

fun TextView.setAnimatedTextColor(color: Int = Color.GREEN, duration: Long = 250, startDelay: Long = 0) {

    val originalColor = textColors.defaultColor

    val valueAnimator = ValueAnimator.ofObject(ArgbEvaluator(), originalColor, color)
    valueAnimator.duration = duration
    valueAnimator.startDelay = startDelay
    valueAnimator.addUpdateListener { animator ->
        setTextColor(animator.animatedValue as Int)
    }

    valueAnimator.start()
}