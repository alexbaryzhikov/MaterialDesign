package com.alexbaryzhikov.seamtostep

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainActivity : Activity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar_layout).title = "Bacon"
    val rv: RecyclerView = findViewById(R.id.recyclerview)
    rv.layoutManager = LinearLayoutManager(this)
    rv.adapter = object : RecyclerView.Adapter<ViewHolder>() {

      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
          ViewHolder(layoutInflater.inflate(R.layout.list_item, parent, false))

      override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text1.text = getString(R.string.item_title)
        holder.text2.text = getString(R.string.item_body)
      }

      override fun getItemCount() = 30
    }
  }

  private class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val text1: TextView = v.findViewById(android.R.id.text1)
    val text2: TextView = v.findViewById(android.R.id.text2)
  }
}
