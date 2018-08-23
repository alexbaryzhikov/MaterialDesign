/*
 * Copyright 2015 Udacity, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.udacity.interpolationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.animation.Interpolator;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class MainActivity extends AppCompatActivity {

  private static final String PACKAGE = "android.view.animation.";
  private static final String PACKAGE_V4 = "android.support.v4.view.animation.";

  @BindView(R.id.interpolator_spinner) Spinner interpolatorSpinner;
  @BindView(R.id.duration_spinner) Spinner durationSpinner;
  @BindView(R.id.textView) TextView textView;

  private int position = 0;
  private int duration = -1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnItemSelected({R.id.duration_spinner})
  void durationSelected(Spinner spinner, int position) {
    if (duration == -1) {
      duration = 900;
    } else {
      String durationString = (String) spinner.getAdapter().getItem(position);
      switch (durationString) {
        case "100 ms":
          duration = 100;
          break;
        case "300 ms":
          duration = 300;
          break;
        case "900 ms":
          duration = 900;
          break;
        case "1500 ms":
          duration = 1500;
          break;
        case "3000 ms":
          duration = 3000;
          break;
      }
    }
    // Kick off transition
    onItemSelected(interpolatorSpinner, this.position);
  }

  String findFullInterpolatorPath(String className) {
    if (className.equals("FastOutLinearInInterpolator") || className.equals("FastOutSlowInInterpolator") || className.equals("LinearOutSlowInInterpolator"))
      return PACKAGE_V4 + className;
    else if (className.startsWith("-"))
      return null;
    else return PACKAGE + className;
  }

  @OnItemSelected({R.id.interpolator_spinner})
  void onItemSelected(Spinner spinner, int position) {
    this.position = position;
    String interpolatorName = (String) spinner.getAdapter().getItem(position);
    DisplayMetrics metrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(metrics);
    int offset = (int) (metrics.heightPixels * 0.5);
    textView.setTranslationY(offset);

    try {
      String path = findFullInterpolatorPath(interpolatorName);
      if (path == null)
        return;

      Interpolator interpolator = (Interpolator) Class.forName(path).newInstance();
      textView.animate().setInterpolator(interpolator)
          .setDuration(duration)
          .setStartDelay(500)
          .translationYBy(-offset)
          .start();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @OnItemSelected(value = R.id.interpolator_spinner, callback = OnItemSelected.Callback.NOTHING_SELECTED)
  void onNothingSelected() {
  }

  @OnClick(R.id.root)
  void replay() {
    onItemSelected(interpolatorSpinner, position);
  }
}
