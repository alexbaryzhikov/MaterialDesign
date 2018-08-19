package com.alexbaryzhikov.transitionmanager

import android.app.Activity
import android.os.Bundle
import android.transition.Scene
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.View
import android.widget.ImageButton

class MainActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val infoButton: ImageButton = findViewById(R.id.info_button)
    infoButton.setOnClickListener(clickListenerInfo)
  }

  private val clickListenerInfo: View.OnClickListener = View.OnClickListener {
    TransitionManager.go(
        Scene.getSceneForLayout(findViewById(R.id.root), R.layout.activity_main_scene_info, this),
        TransitionInflater.from(this).inflateTransition(R.transition.default_to_info))
    val closeButton: ImageButton = findViewById(R.id.close_button)
    closeButton.setOnClickListener(clickListenerClose)
  }

  private val clickListenerClose: View.OnClickListener = View.OnClickListener {
    TransitionManager.go(
        Scene.getSceneForLayout(findViewById(R.id.root), R.layout.activity_main, this),
        TransitionInflater.from(this).inflateTransition(R.transition.info_to_default))
    val infoButton: ImageButton = findViewById(R.id.info_button)
    infoButton.setOnClickListener(clickListenerInfo)
  }
}
