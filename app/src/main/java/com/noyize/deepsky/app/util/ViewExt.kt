package com.noyize.deepsky.app.util

import android.view.View

fun View.animateAndShow() {
    visibility = View.VISIBLE
    alpha = 0.0f

    animate().setStartDelay(400)
        .translationY(0f)
        .alpha(1f)
        .setListener(null)
}