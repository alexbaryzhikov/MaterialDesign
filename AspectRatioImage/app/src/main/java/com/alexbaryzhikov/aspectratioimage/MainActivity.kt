package com.alexbaryzhikov.aspectratioimage

import android.app.Activity
import android.os.Bundle
import com.bumptech.glide.Glide

class MainActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val imageView: AspectImageView = findViewById(R.id.image)
    Glide.with(this).load(R.raw.blue_balcony).into(imageView)
  }
}
