package com.alexbaryzhikov.buttonanimation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.Button

class MainActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val eatme: Button = findViewById(R.id.eatme)
    eatme.setOnClickListener {
      val y = eatme.y
      it.animate()
          .alpha(0f)
          .translationY(-200f)
          .setInterpolator(AccelerateInterpolator())
          .setDuration(resources.getInteger(android.R.integer.config_shortAnimTime).toLong())
          .withEndAction {
            it.visibility = View.GONE
            it.alpha = 1f
            it.y = y
            startActivity(Intent(it.context, SubActivity::class.java))
            overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_top)
          }
    }
  }

  override fun onStart() {
    super.onStart()
    val eatme: Button = findViewById(R.id.eatme)
    eatme.visibility = View.VISIBLE
  }
}
