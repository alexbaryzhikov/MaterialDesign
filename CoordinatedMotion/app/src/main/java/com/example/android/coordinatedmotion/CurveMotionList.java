package com.example.android.coordinatedmotion;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CurveMotionList extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_curved_motion_grid);

    RecyclerView list = findViewById(R.id.list);
    list.setLayoutManager(new LinearLayoutManager(this));
    list.setAdapter(new MessageAdapter(this));
    list.setHasFixedSize(true);
  }

  static class MessageHolder extends RecyclerView.ViewHolder {

    View avatar;
    View title;
    View subtitle;

    MessageHolder(View itemView) {
      super(itemView);
      avatar = itemView.findViewById(R.id.avatar);
      title = itemView.findViewById(R.id.title);
      subtitle = itemView.findViewById(R.id.subtitle);
    }
  }

  public class MessageAdapter extends RecyclerView.Adapter<MessageHolder> {

    private final int[] COLORS = new int[]{0xff956689, 0xff80678A, 0xff6A6788, 0xff546683,
        0xff3F657B, 0xff3F657B};
    private final LayoutInflater inflater;
    private Activity host;

    MessageAdapter(Activity activity) {
      host = activity;
      inflater = LayoutInflater.from(host);
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new MessageHolder(inflater.inflate(R.layout.message, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageHolder holder, int position) {
      final int color = COLORS[position % COLORS.length];
      holder.avatar.setBackgroundTintList(ColorStateList.valueOf(color));
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent = new Intent(host, CurvedMotionDetail.class);
          boolean curve = (holder.getAdapterPosition() % 2 == 0);
          intent.putExtra(CurvedMotionDetail.EXTRA_COLOR, color);
          intent.putExtra(CurvedMotionDetail.EXTRA_CURVE, curve);
          host.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(
              host, holder.avatar, holder.avatar.getTransitionName()).toBundle());
        }
      });
    }

    @Override
    public int getItemCount() {
      return 64;
    }
  }
}
