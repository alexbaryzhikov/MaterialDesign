package com.alexbaryzhikov.circularreveal

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.ViewAnimationUtils
import android.widget.FrameLayout

class MainActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val frame: FrameLayout = findViewById(R.id.frame)
    val turquoise = resources.getColor(R.color.turquoise)
    val white = resources.getColor(R.color.white)

    Log.i("MainActivity", turquoise.toString())
    Log.i("MainActivity", white.toString())

    frame.setOnClickListener {
      val isRevealed = it.background != null && (it.background as ColorDrawable).color == turquoise
      val radius = Math.hypot(it.width / 2.0, it.height / 2.0).toFloat()

      if (isRevealed) {
        it.setBackgroundColor(white)
      } else {
        val animator = ViewAnimationUtils.createCircularReveal(it,
            it.width / 2, it.height / 2, 0f, radius)
        it.setBackgroundColor(turquoise)
        animator.start()
      }
    }
  }
}
