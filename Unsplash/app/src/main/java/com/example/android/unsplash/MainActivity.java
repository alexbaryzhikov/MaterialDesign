package com.example.android.unsplash;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.unsplash.data.UnsplashService;
import com.example.android.unsplash.data.model.Photo;
import com.example.android.unsplash.ui.ItemClickSupport;
import com.example.android.unsplash.ui.PhotoAdapter;

import java.util.List;

import butterknife.BindDimen;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends Activity {

  private static final int PHOTO_COUNT = 12;

  @BindView(R.id.image_grid) RecyclerView grid;
  @BindView(android.R.id.empty) ProgressBar empty;
  @BindInt(R.integer.photo_grid_columns) int columns;
  @BindDimen(R.dimen.grid_item_spacing) int gridSpacing;

  private PhotoAdapter adapter;
  private String photoUrlBase;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    final int widthPixels = getResources().getDisplayMetrics().widthPixels;
    photoUrlBase = "https://unsplash.it/" + widthPixels + "?image=";
    setupGrid();
    loadPhotos();
  }

  private void setupGrid() {
    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, columns);
    gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override
      public int getSpanSize(int position) {
        // Emulating https://material-design.storage.googleapis.com/publish/material_v_4/material_ext_publish/0B6Okdz75tqQsck9lUkgxNVZza1U/style_imagery_integration_scale1.png
        switch (position % 6) {
          case 3:
            return 2;
          case 5:
            return 3;
          default:
            return 1;
        }
      }
    });
    grid.setLayoutManager(gridLayoutManager);
    adapter = new PhotoAdapter(photoUrlBase);
    grid.setAdapter(adapter);
    grid.addItemDecoration(new RecyclerView.ItemDecoration() {
      @Override
      public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = gridSpacing;
        outRect.top = gridSpacing;
        outRect.right = gridSpacing;
        outRect.bottom = gridSpacing;
      }
    });
    grid.setHasFixedSize(true);

    ItemClickSupport.addTo(grid).setOnItemClickListener((recyclerView, position, view) -> {
      Photo photo = adapter.getItem(position);
      Intent intent = new Intent(this, DetailActivity.class);
      intent.setAction(Intent.ACTION_VIEW);
      intent.setData(Uri.parse(photoUrlBase + photo.id));
      intent.putExtra(DetailActivity.EXTRA_AUTHOR, photo.author);
      Bundle options = ActivityOptions
          .makeSceneTransitionAnimation(this, view, view.getTransitionName())
          .toBundle();
      startActivity(intent, options);
    });
  }

  private void loadPhotos() {
    UnsplashService unsplashApi = new RestAdapter.Builder()
        .setEndpoint(UnsplashService.ENDPOINT)
        .build()
        .create(UnsplashService.class);

    unsplashApi.getFeed(new Callback<List<Photo>>() {
      @Override
      public void success(List<Photo> photos, Response response) {
        // The first items are really boring, get the last <n>
        adapter.setPhotos(photos.subList(photos.size() - PHOTO_COUNT, photos.size()));
        empty.setVisibility(View.GONE);
      }

      @Override
      public void failure(RetrofitError error) {
        Log.e(getClass().getCanonicalName(), "Error retrieving Unsplash feed:", error);
      }
    });
  }
}
