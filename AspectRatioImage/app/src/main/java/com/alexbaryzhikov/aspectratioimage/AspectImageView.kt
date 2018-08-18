package com.alexbaryzhikov.aspectratioimage

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView

class AspectImageView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {

  private var widthRatio = 1
  private var heightRatio = 1

  init {
    context.theme.obtainStyledAttributes(
        attrs, R.styleable.AspectImageView, 0, 0).apply {
      widthRatio = getInteger(R.styleable.AspectImageView_widthRatio, 1)
      heightRatio = getInteger(R.styleable.AspectImageView_heightRatio, 1)
    }
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    val threeTwoHeight = MeasureSpec.getSize(widthMeasureSpec) * heightRatio / widthRatio
    val threeTwoHeightSpec = MeasureSpec.makeMeasureSpec(threeTwoHeight, MeasureSpec.EXACTLY)
    Log.i(javaClass.simpleName, "${MeasureSpec.getSize(widthMeasureSpec)} x $threeTwoHeight")
    super.onMeasure(widthMeasureSpec, threeTwoHeightSpec)
  }
}
