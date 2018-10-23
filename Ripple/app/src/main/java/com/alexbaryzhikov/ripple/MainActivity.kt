package com.alexbaryzhikov.ripple

import android.app.Activity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val fab = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fab)
    fab.setOnClickListener { }
  }
}
