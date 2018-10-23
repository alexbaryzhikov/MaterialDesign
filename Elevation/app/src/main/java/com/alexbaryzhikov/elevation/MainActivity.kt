package com.alexbaryzhikov.elevation

import android.app.Activity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.FrameLayout

class MainActivity : Activity() {

  private val _risen = 20f

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val frame1 = findViewById<FrameLayout>(R.id.frame1)
    val frame2 = findViewById<FrameLayout>(R.id.frame2)
    val frame3 = findViewById<FrameLayout>(R.id.frame3)
    val frame4 = findViewById<FrameLayout>(R.id.frame4)

    val e1: Float = frame1.elevation
    val e2: Float = frame2.elevation
    val e3: Float = frame3.elevation
    val e4: Float = frame4.elevation

    val risen = dpToPx(_risen)

    frame1.setOnClickListener { it.elevation = if (it.elevation == risen) e1 else risen }
    frame2.setOnClickListener { it.elevation = if (it.elevation == risen) e2 else risen }
    frame3.setOnClickListener { it.elevation = if (it.elevation == risen) e3 else risen }
    frame4.setOnClickListener { it.elevation = if (it.elevation == risen) e4 else risen }

    val fab = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fab)

    fab.setOnClickListener {
      frame1.elevation = e1
      frame2.elevation = e2
      frame3.elevation = e3
      frame4.elevation = e4
    }
  }

  private fun dpToPx(dps: Float): Float {
    return dps * resources.displayMetrics.density + 0.5f
  }
}
