package com.alexbaryzhikov.ripple

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton

class MainActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val fab = findViewById<FloatingActionButton>(R.id.fab)
    fab.setOnClickListener { }
  }
}
