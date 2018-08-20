package com.alexbaryzhikov.activitytransition

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView

private val images = listOf(R.id.pic0, R.id.pic1, R.id.pic2, R.id.pic3, R.id.pic4, R.id.pic5)

class MainActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    images.forEachIndexed { i, image ->
      val imageView = findViewById<ImageView>(image)
      val intent = Intent(this, DetailActivity::class.java)
      intent.putExtra("id", i)
      imageView.setOnClickListener {
        val bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        startActivity(intent, bundle)
      }
    }
  }
}
