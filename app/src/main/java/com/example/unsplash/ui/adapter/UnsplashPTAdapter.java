package com.example.unsplash.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.upsplash.R;
import com.example.unsplash.data.dto.UnsplashPhoto;
import com.example.unsplash.ui.activity.PictureDetailActivity;

import java.util.ArrayList;


public class UnsplashPTAdapter extends RecyclerView.Adapter<UnsplashPTAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<UnsplashPhoto> pts = new ArrayList<>();

    public UnsplashPTAdapter(Context mContext, ArrayList<UnsplashPhoto> pts) {
        this.mContext = mContext;
        this.pts = pts;
    }

    public void addAll(ArrayList<UnsplashPhoto> data) {
        pts.clear();
        pts.addAll(data);
        notifyDataSetChanged();
    }

    public void loadMore(ArrayList<UnsplashPhoto> data) {
        pts.addAll(data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pt, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(pts.get(position));
    }

    @Override
    public int getItemCount() {
        return pts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_content;

        ViewHolder(View itemView) {
            super(itemView);
            img_content = itemView.findViewById(R.id.img_content);
        }

        void bind(UnsplashPhoto data) {

            Glide.with(mContext)
                    .load(data.getUrl())
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(img_content);
            img_content.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, PictureDetailActivity.class);
                intent.putExtra("pic_url", data.getUrl());
                mContext.startActivity(intent);
            });
        }
    }
}

