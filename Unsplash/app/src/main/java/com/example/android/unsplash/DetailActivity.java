package com.example.android.unsplash;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends Activity {

  public static final String EXTRA_AUTHOR = "EXTRA_AUTHOR";

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.photo) ImageView photo;
  @BindView(R.id.author) TextView author;
  @BindInt(R.integer.detail_desc_slide_duration) int slideDuration;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    ButterKnife.bind(this);

    Intent intent = getIntent();
    Picasso.get().load(intent.getData()).placeholder(R.color.placeholder).into(photo);
    author.setText(getString(R.string.author, intent.getStringExtra(EXTRA_AUTHOR)));
    toolbar.setNavigationOnClickListener(v -> finishAfterTransition());

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Transition slide = new Slide(Gravity.BOTTOM)
          .addTarget(R.id.description)
          .setInterpolator(AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in))
          .setDuration(slideDuration);
      getWindow().setEnterTransition(slide);
    }
  }
}
