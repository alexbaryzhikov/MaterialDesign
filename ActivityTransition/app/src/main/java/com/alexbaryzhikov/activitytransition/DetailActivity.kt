package com.alexbaryzhikov.activitytransition

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)
    setupActivity()
  }

  override fun onNavigateUp(): Boolean {
    onBackPressed()
    return true
  }

  private fun setupActivity() {
    actionBar.setDisplayHomeAsUpEnabled(true)
    val id = intent.getIntExtra("id", -1)
    findViewById<ImageView>(R.id.image)
        .setImageDrawable(resources.obtainTypedArray(R.array.images).getDrawable(id))
    findViewById<TextView>(R.id.title)
        .text = resources.getStringArray(R.array.titles)[id]
    findViewById<TextView>(R.id.description)
        .text = resources.getStringArray(R.array.descriptions)[id]
  }
}
