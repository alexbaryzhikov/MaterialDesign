package com.alexbaryzhikov.activitytransition

import android.app.Activity
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionSet
import android.view.Gravity
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)

    setupViews()
    setupEnterTransition()
    setupExitTransition()
  }

  private fun setupViews() {
    val id = intent.getIntExtra("id", -1)
    val backButton = findViewById<ImageButton>(R.id.back_button)
    val image = findViewById<ImageView>(R.id.image)
    val title = findViewById<TextView>(R.id.title)
    val description = findViewById<TextView>(R.id.description)
    backButton.setOnClickListener {
      onBackPressed()
    }
    image.setImageDrawable(resources.obtainTypedArray(R.array.images).getDrawable(id))
    title.text = resources.getStringArray(R.array.titles)[id]
    description.text = resources.getStringArray(R.array.descriptions)[id]
  }

  private fun setupEnterTransition() {
    val fadeIn = Fade(Fade.IN)
    fadeIn.addTarget(R.id.image)
    fadeIn.addTarget(R.id.back_button)
    fadeIn.addTarget(R.id.title)
    val slideIn = Slide(Gravity.BOTTOM)
    slideIn.addTarget(R.id.description)
    slideIn.interpolator = AnimationUtils.loadInterpolator(this,
        android.R.interpolator.linear_out_slow_in)
    val inSet = TransitionSet()
    inSet.addTransition(fadeIn)
    inSet.addTransition(slideIn)
    window.enterTransition = inSet
  }

  private fun setupExitTransition() {
    val fadeOut = Fade(Fade.OUT)
    fadeOut.addTarget(R.id.image)
    fadeOut.addTarget(R.id.back_button)
    fadeOut.addTarget(R.id.title)
    fadeOut.addTarget(R.id.description)
    val slideOut = Slide(Gravity.BOTTOM)
    slideOut.addTarget(R.id.image)
    slideOut.addTarget(R.id.back_button)
    slideOut.addTarget(R.id.title)
    slideOut.addTarget(R.id.description)
    val outSet = TransitionSet()
    outSet.addTransition(fadeOut)
    outSet.addTransition(slideOut)
    window.returnTransition = outSet
  }

}
