/*
 * Copyright 2014 The Android Open Source Project
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
package com.example.android.scrolleventsdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ((CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout)).setTitle("Screen Title");
    RecyclerView rv = findViewById(R.id.recyclerview);
    rv.setLayoutManager(new LinearLayoutManager(this));
    rv.setAdapter(new RecyclerView.Adapter<ViewHolder>() {

      @NonNull
      @Override
      public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new ViewHolder(getLayoutInflater().inflate(R.layout.list_item, parent, false));
      }

      @Override
      public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.text1.setText(R.string.bacon);
        viewHolder.text2.setText(R.string.bacon_text);
      }

      @Override
      public int getItemCount() {
        return 30;
      }
    });
  }

  private static class ViewHolder extends RecyclerView.ViewHolder {
    TextView text1;
    TextView text2;

    ViewHolder(View itemView) {
      super(itemView);
      text1 = itemView.findViewById(android.R.id.text1);
      text2 = itemView.findViewById(android.R.id.text2);
    }
  }
}
