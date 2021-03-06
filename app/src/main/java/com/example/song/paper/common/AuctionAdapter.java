package com.example.song.paper.common;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.donkingliang.imageselector.view.SquareImageView;
import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.auction.AuctionDetailActivity;
import com.example.song.paper.global.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuctionAdapter extends RecyclerView.Adapter<AuctionAdapter.ViewHolder> {
    private Context context;
    private List<AuctionBean> list;

    public AuctionAdapter(Context context, List<AuctionBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_homepage1, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideApp.with(context)
                .load(AppConstant.Base_Url+list.get(position).getImage())
                .placeholder(R.drawable.imageholder)
                .override(200,200)
                .into(holder.image);
        holder.price.setText("当前价：¥"+list.get(position).getPrice_now());
        holder.name.setText("拍品名："+list.get(position).getName());
        holder.time.setVisibility(View.GONE);
        holder.numAuction.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(context,AuctionDetailActivity.class);
            intent.putExtra("id",list.get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        SquareImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.num_auction)
        TextView numAuction;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
