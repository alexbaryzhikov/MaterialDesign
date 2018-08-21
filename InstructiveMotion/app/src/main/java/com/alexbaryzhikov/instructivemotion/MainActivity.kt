package com.alexbaryzhikov.instructivemotion

import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ScrollView

class MainActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }

  override fun onEnterAnimationComplete() {
    super.onEnterAnimationComplete()
    val scrollView = findViewById<ScrollView>(R.id.scroll_view)
    val startScrollPos = resources.getDimensionPixelSize(R.dimen.init_scroll_up_distance)
    val animator = ObjectAnimator.ofInt(scrollView, "scrollY", startScrollPos)
        .setDuration(300)
    animator.interpolator = AccelerateDecelerateInterpolator()
    animator.start()
  }
}
