package com.noyize.deepsky.app.util

import androidx.viewpager2.widget.ViewPager2

inline fun ViewPager2.onPageChanged(crossinline pageChanged: (Int) -> Unit) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            pageChanged(position)
        }
    })
}