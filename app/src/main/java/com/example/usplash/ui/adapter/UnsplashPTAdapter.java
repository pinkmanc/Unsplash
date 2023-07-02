package com.example.usplash.ui.adapter;

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
import com.example.usplash.data.dto.UnsplashPhoto;

import java.util.ArrayList;


public class UnsplashPTAdapter extends RecyclerView.Adapter<UnsplashPTAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<UnsplashPhoto> mzs = new ArrayList<>();

    public UnsplashPTAdapter(Context mContext, ArrayList<UnsplashPhoto> mzs) {
        this.mContext = mContext;
        this.mzs = mzs;
    }

    public void addAll(ArrayList<UnsplashPhoto> data) {
        mzs.clear();
        mzs.addAll(data);
        notifyDataSetChanged();
    }

    public void loadMore(ArrayList<UnsplashPhoto> data) {
        mzs.addAll(data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pt, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mzs.get(position));
    }

    @Override
    public int getItemCount() {
        return mzs.size();
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

