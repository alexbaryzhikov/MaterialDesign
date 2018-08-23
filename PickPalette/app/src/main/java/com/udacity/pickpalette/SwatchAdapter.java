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

import android.content.Context;
import android.support.v7.graphics.Palette;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

public class SwatchAdapter extends BaseAdapter {

  private Context context;
  private List<Pair<String, Palette.Swatch>> items;

  SwatchAdapter(Context context, List<Pair<String, Palette.Swatch>> items) {
    this.context = context;
    this.items = items;
  }

  @Override
  public int getCount() {
    return items != null ? items.size() : 0;
  }

  @Override
  public Object getItem(int position) {
    return items.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      convertView = inflater.inflate(R.layout.swatch_view, parent, false);
    }
    Pair<String, Palette.Swatch> item = items.get(position);
    int colorValue = Objects.requireNonNull(item).second.getRgb();
    String color = context.getString(R.string.color_title, item.first,
        Integer.toHexString(item.second.getRgb()).toUpperCase());
    convertView.findViewById(R.id.color_swatch).setBackgroundColor(colorValue);
    ((TextView) convertView.findViewById(R.id.color_title)).setText(color);
    return convertView;
  }

  public Pair<String, Palette.Swatch> getTypedItem(int position) {
    return items.get(position);
  }
}
