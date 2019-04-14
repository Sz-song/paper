package com.example.song.paper.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donkingliang.imageselector.view.SquareImageView;
import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.global.GlideApp;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadPhotoAdapter extends RecyclerView.Adapter<UploadPhotoAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> list;
    public ItemClickListener listener;

    public UploadPhotoAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.cell_upload_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final String image = list.get(position);
        if (image.contains("add_pic")) {
            String sub = image.substring(7);
            int id = Integer.parseInt(sub);
            GlideApp.with(context)
                    .load(id)
                    .override(100, 100)
                    .into(holder.image);
        } else if (image.contains("img/")) {
            GlideApp.with(context)
                    .load(AppConstant.Base_Url + image)
                    .override(100, 100)
                    .placeholder(R.drawable.imageholder)
                    .into(holder.image);
        } else {
            GlideApp.with(context)
                    .load(new File(image))
                    .override(100, 100)
                    .placeholder(R.drawable.imageholder)
                    .into(holder.image);
        }
        holder.itemView.setOnClickListener(v -> listener.onItemClick(position, holder.image));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        SquareImageView image;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface ItemClickListener {
        void onItemClick(int position, View view);
    }
}
