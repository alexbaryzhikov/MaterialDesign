package com.alexbaryzhikov.aspectratioimage

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import kotlin.math.roundToInt

class AspectImageView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {

  private val ratio = {
    val styledAttrs = context.theme.obtainStyledAttributes(attrs, R.styleable.AspectImageView, 0, 0)
    val w = styledAttrs.getInteger(R.styleable.AspectImageView_ratioX, 1)
    val h = styledAttrs.getInteger(R.styleable.AspectImageView_ratioY, 1)
    styledAttrs.recycle()
    h / w.toDouble()
  }.invoke()

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    val height = (MeasureSpec.getSize(widthMeasureSpec) * ratio).roundToInt()
    Log.i(javaClass.simpleName, "${MeasureSpec.getSize(widthMeasureSpec)} : $height")
    super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY))
  }
}
