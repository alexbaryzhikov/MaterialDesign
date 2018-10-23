package com.example.android.unsplash.ui;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.unsplash.R;
import com.example.android.unsplash.data.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

  private List<Photo> photos;
  private final String photoUrlBase;

  public PhotoAdapter(String photoUrlBase) {
    this.photoUrlBase = photoUrlBase;
  }

  @NonNull
  @Override
  public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View itemView = inflater.inflate(R.layout.photo_item, parent, false);
    return new PhotoViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
    String url = photoUrlBase + getItemId(position);
    Picasso.get().load(url).placeholder(R.color.placeholder).into(holder.imageView);
  }

  @Override
  public int getItemCount() {
    return photos != null ? photos.size() : 0;
  }

  @Override
  public long getItemId(int position) {
    return photos.get(position).id;
  }

  public Photo getItem(int position) {
    return photos.get(position);
  }

  public void setPhotos(List<Photo> photos) {
    this.photos = photos;
    notifyDataSetChanged();
  }

  /** PhotoViewHolder */
  static class PhotoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.photo) ForegroundImageView imageView;

    PhotoViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
