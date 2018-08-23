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
package com.udacity.pickpalette;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MainActivity extends Activity {

  @BindView(R.id.fab) FloatingActionButton fab;
  @BindView(R.id.grid_view) GridView gridView;
  @BindView(R.id.imageView) ImageView imageView;

  SwatchAdapter swatchAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.fab)
  public void click(View view) {
    Snackbar.make(findViewById(R.id.fragment), "Clicked FAB.", Snackbar.LENGTH_LONG)
        //.setAction("Action", this)
        .show();
    FragmentManager fm = getFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    PickerFragment pickerFragment = new PickerFragment();
    pickerFragment.show(getFragmentManager(), "dialog");
    ft.commit();
  }

  public void createPalette(Object object) {
    Bitmap bitmap;
    try {
      if (object instanceof Uri) {
        Uri imageUri = (Uri) object;
        Picasso.get().load(imageUri).into(imageView);
        InputStream imageStream = getContentResolver().openInputStream(imageUri);
        bitmap = BitmapFactory.decodeStream(imageStream);
      } else {
        bitmap = (Bitmap) object;
        imageView.setImageBitmap(bitmap);
      }
      // Do this async on activity
      Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(@NonNull Palette palette) {
          swatchAdapter = new SwatchAdapter(getApplicationContext(), processPalette(palette));
          gridView.setAdapter(swatchAdapter);
        }
      });
    } catch (Exception ex) {
      Log.e("MainActivity", "error in creating palette");
    }
  }

  List<Pair<String, Palette.Swatch>> processPalette(Palette p) {
    List<Pair<String, Palette.Swatch>> items = new ArrayList<>();

    if (p.getVibrantSwatch() != null)
      items.add(new Pair<>("Vibrant", p.getVibrantSwatch()));
    if (p.getDarkVibrantSwatch() != null)
      items.add(new Pair<>("DarkVibrant", p.getDarkVibrantSwatch()));
    if (p.getLightVibrantSwatch() != null)
      items.add(new Pair<>("LightVibrant", p.getLightVibrantSwatch()));

    if (p.getMutedSwatch() != null)
      items.add(new Pair<>("Muted", p.getMutedSwatch()));
    if (p.getDarkMutedSwatch() != null)
      items.add(new Pair<>("DarkMuted", p.getDarkMutedSwatch()));
    if (p.getLightMutedSwatch() != null)
      items.add(new Pair<>("LightMuted", p.getLightMutedSwatch()));

    return items;
  }

  @OnItemClick(R.id.grid_view)
  void onItemClick(int position) {
    Palette.Swatch swatch = swatchAdapter.getTypedItem(position).second;
    String b = "Title Text Color: " + "#" +
        Integer.toHexString(swatch.getBodyTextColor()).toUpperCase() + "\n" +
        "Population: " + swatch.getPopulation();
    Snackbar.make(gridView, b, Snackbar.LENGTH_LONG).show();
  }
}
