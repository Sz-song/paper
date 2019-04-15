package com.example.song.paper.home.homepage;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.donkingliang.banner.CustomBanner;
import com.donkingliang.imageselector.view.SquareImageView;
import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.auction.AuctionDetailActivity;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.global.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageAdpter extends RecyclerView.Adapter {
    private Context context;
    private List<AuctionBean> list;
    private List<String> bannerimages;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public HomePageAdpter(Context context, List<AuctionBean> list, List<String> bannerimages) {
        this.context = context;
        this.list = list;
        this.bannerimages = bannerimages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.cell_homepage0, viewGroup, false);
            return new ViewHolder0(view);
        } else if (viewType == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.cell_homepage1, viewGroup, false);
            return new HomePageAdpter.ViewHolder1(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder0){
            ((ViewHolder0) holder).banner.setPages(new CustomBanner.ViewCreator<String>() {
                @Override
                public View createView(Context context, int i) {
                    ImageView imageView = new ImageView(context);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    return imageView;
                }
                @Override
                public void updateUI(Context context, View view, int i, String s) {
                    GlideApp.with(context)
                            .load(AppConstant.Base_Url+s)
                            .placeholder(R.drawable.imageholder)
                            .override(400,200)
                            .into((ImageView) view);
                }
            },bannerimages);
            ((ViewHolder0) holder).banner.startTurning(3000);
            ((ViewHolder0) holder).banner.setScrollDuration(550);
            ((ViewHolder0) holder).banner.setOnPageClickListener((i, o) -> {
                Intent intent=new Intent(context,AuctionDetailActivity.class);
                intent.putExtra("id","1");
                context.startActivity(intent);
            });
        }else if(holder instanceof ViewHolder1){
            GlideApp.with(context)
                    .load(AppConstant.Base_Url+list.get(position-1).getImage())
                    .placeholder(R.drawable.imageholder)
                    .override(200,200)
                    .into(((ViewHolder1) holder).image);
            ((ViewHolder1) holder).price.setText("¥"+list.get(position-1).getPrice_now());
            ((ViewHolder1) holder).name.setText(list.get(position-1).getName());
            ((ViewHolder1) holder).time.setText(list.get(position-1).getId());
            ((ViewHolder1) holder).numAuction.setText(list.get(position-1).getName());
            holder.itemView.setOnClickListener(v -> {
                Intent intent=new Intent(context,AuctionDetailActivity.class);
                intent.putExtra("id",list.get(position-1).getId());
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    static class ViewHolder0 extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        CustomBanner banner;
        ViewHolder0(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder1 extends  RecyclerView.ViewHolder {
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

        ViewHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
