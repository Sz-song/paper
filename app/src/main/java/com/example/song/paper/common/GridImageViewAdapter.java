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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by song on 2019-03-11.
 */

public class GridImageViewAdapter extends RecyclerView.Adapter<GridImageViewAdapter.ViewHolder> {
    private Context context;
    private List<String> list;
    public GridImageViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.cell_grid_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (null == list.get(position) || list.get(position).equals("")) {
            return;
        }
        GlideApp.with(context)
                .load(AppConstant.Base_Url +list.get(position))
                .override(300,300)
                .placeholder(R.drawable.imageholder)
                .into(holder.gridImage);
        holder.gridImage.setOnClickListener(v -> {
            //Todo 查看大图
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.grid_image)
        SquareImageView gridImage;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
