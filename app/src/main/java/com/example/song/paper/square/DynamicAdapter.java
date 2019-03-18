package com.example.song.paper.square;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.common.GridImageViewAdapter;
import com.example.song.paper.global.GlideApp;
import com.example.song.paper.utils.ParseUtils;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DynamicAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<DynamicBean> list;

    DynamicAdapter(Context context, List<DynamicBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if(list!=null&&list.get(position)!=null) {
            if (null == list.get(position).getImages() || list.get(position).getImages().size() == 0) {
                return 0;
            } else if (null != list.get(position).getImages() && list.get(position).getImages().size() == 1) {
                return 1;
            } else if (null != list.get(position).getImages() && list.get(position).getImages().size() > 1) {
                return 2;
            }else{
                return 3;
            }
        }else{
            return 3;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.cell_dynamic0, viewGroup, false);
            return new ViewHolder0(view);
        } else if (viewType == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.cell_dynamic1, viewGroup, false);
            return new ViewHolder1(view);
        } else if (viewType == 2) {
            view = LayoutInflater.from(context).inflate(R.layout.cell_dynamic2, viewGroup, false);
            return new ViewHolder2(view);
        }else{
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            //Todo 详情页跳转
        });
        if (holder instanceof ViewHolder0) {
            GlideApp.with(context)
                    .load(AppConstant.Base_Url+list.get(position).getPortrait())
                    .placeholder(R.drawable.imageholder)
                    .override(100,100)
                    .into(((ViewHolder0) holder).portrait);
            ((ViewHolder0) holder).name.setText(list.get(position).getName());
            ((ViewHolder0) holder).time.setText(ParseUtils.CountTime(list.get(position).getTime()));
            ((ViewHolder0) holder).content.setText(list.get(position).getContent());
            ((ViewHolder0) holder).pinglunNum.setText(list.get(position).getPinglun_num());
            ((ViewHolder0) holder).likeNum.setText(list.get(position).getDianzan_num());
            if(list.get(position).isIsdianzan()){
                GlideApp.with(context).load(R.drawable.ic_like_red).into(((ViewHolder0) holder).likeImg);
            }else{
                GlideApp.with(context).load(R.drawable.ic_like_white).into(((ViewHolder0) holder).likeImg);
            }
            ((ViewHolder0) holder).pinglun.setOnClickListener(v -> {
                //Todo pinglun
            });
            ((ViewHolder0) holder).like.setOnClickListener(v ->{
                //Todo 点赞
            });
        }else if (holder instanceof ViewHolder1) {
            GlideApp.with(context)
                    .load(AppConstant.Base_Url+list.get(position).getPortrait())
                    .placeholder(R.drawable.imageholder)
                    .override(100,100)
                    .into(((ViewHolder1) holder).portrait);
            GlideApp.with(context)
                    .load(AppConstant.Base_Url+list.get(position).getImages().get(0))
                    .placeholder(R.drawable.imageholder)
                    .override(400,400)
                    .into(((ViewHolder1) holder).image);
            ((ViewHolder1) holder).name.setText(list.get(position).getName());
            ((ViewHolder1) holder).time.setText(ParseUtils.CountTime(list.get(position).getTime()));
            ((ViewHolder1) holder).content.setText(list.get(position).getContent());
            ((ViewHolder1) holder).pinglunNum.setText(list.get(position).getPinglun_num());
            ((ViewHolder1) holder).likeNum.setText(list.get(position).getDianzan_num());
            if(list.get(position).isIsdianzan()){
                GlideApp.with(context).load(R.drawable.ic_like_red).into(((ViewHolder1) holder).likeImg);
            }else{
                GlideApp.with(context).load(R.drawable.ic_like_white).into(((ViewHolder1) holder).likeImg);
            }
            ((ViewHolder1) holder).pinglun.setOnClickListener(v -> {
                //Todo pinglun
            });
            ((ViewHolder1) holder).like.setOnClickListener(v ->{
                //Todo 点赞
            });
        }else if (holder instanceof ViewHolder2) {
            GlideApp.with(context)
                    .load(AppConstant.Base_Url+list.get(position).getPortrait())
                    .placeholder(R.drawable.imageholder)
                    .override(100,100)
                    .into(((ViewHolder2) holder).portrait);
            ((ViewHolder2) holder).name.setText(list.get(position).getName());
            ((ViewHolder2) holder).time.setText(ParseUtils.CountTime(list.get(position).getTime()));
            ((ViewHolder2) holder).content.setText(list.get(position).getContent());
            ((ViewHolder2) holder).pinglunNum.setText(list.get(position).getPinglun_num());
            ((ViewHolder2) holder).likeNum.setText(list.get(position).getDianzan_num());
            if(list.get(position).isIsdianzan()){
                GlideApp.with(context).load(R.drawable.ic_like_red).into(((ViewHolder2) holder).likeImg);
            }else{
                GlideApp.with(context).load(R.drawable.ic_like_white).into(((ViewHolder2) holder).likeImg);
            }
            GridLayoutManager manager = new GridLayoutManager(context, 3){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            ((ViewHolder2) holder).recyclerview.setLayoutManager(manager);
            GridImageViewAdapter adapter = new GridImageViewAdapter (context, list.get(position).getImages());
            ((ViewHolder2) holder).recyclerview.setAdapter(adapter);
            ((ViewHolder2) holder).pinglun.setOnClickListener(v -> {
                //Todo pinglun
            });
            ((ViewHolder2) holder).like.setOnClickListener(v ->{
                //Todo 点赞
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder0 extends RecyclerView.ViewHolder {
        @BindView(R.id.portrait)
        CircleImageView portrait;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.pinglun_num)
        TextView pinglunNum;
        @BindView(R.id.pinglun)
        RelativeLayout pinglun;
        @BindView(R.id.like_num)
        TextView likeNum;
        @BindView(R.id.like)
        RelativeLayout like;
        @BindView(R.id.like_img)
        ImageView likeImg;
        ViewHolder0(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.portrait)
        CircleImageView portrait;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.pinglun_num)
        TextView pinglunNum;
        @BindView(R.id.pinglun)
        RelativeLayout pinglun;
        @BindView(R.id.like_num)
        TextView likeNum;
        @BindView(R.id.like)
        RelativeLayout like;
        @BindView(R.id.like_img)
        ImageView likeImg;
        ViewHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.portrait)
        CircleImageView portrait;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.recyclerview)
        RecyclerView recyclerview;
        @BindView(R.id.pinglun_num)
        TextView pinglunNum;
        @BindView(R.id.pinglun)
        RelativeLayout pinglun;
        @BindView(R.id.like_num)
        TextView likeNum;
        @BindView(R.id.like)
        RelativeLayout like;
        @BindView(R.id.like_img)
        ImageView likeImg;
        ViewHolder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
