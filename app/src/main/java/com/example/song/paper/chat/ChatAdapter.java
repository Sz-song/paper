package com.example.song.paper.chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.global.GlideApp;
import com.example.song.paper.utils.ParseUtils;
import com.example.song.paper.utils.Sp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ChatBean> list;
    private String name;

    public ChatAdapter(Context context, List<ChatBean> list,String name) {
        this.context = context;
        this.list = list;
        this.name=name;
    }
    @Override
    public int getItemViewType(int position) {
        if(list.get(position).getUserid().equals(Sp.getString(context,AppConstant.UID))){
            return 1;
        }else {
            return 0;
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.cell_chat0, viewGroup, false);
            return new ViewHolder0(view);
        } else if (viewType == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.cell_chat1, viewGroup, false);
            return new ViewHolder1(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder0 ) {
            GlideApp.with(context)
                    .load(AppConstant.Base_Url + list.get(position).getPortrait())
                    .placeholder(R.drawable.imageholder)
                    .override(200, 200)
                    .into(((ViewHolder0) holder).portrait);
            ((ViewHolder0) holder).message.setText(list.get(position).getContent());
            ((ViewHolder0) holder).name.setText(name);
            ((ViewHolder0) holder).time.setText(ParseUtils.CountTime(list.get(position).getTime()));
        }else if(holder instanceof ViewHolder1){
            GlideApp.with(context)
                    .load(AppConstant.Base_Url + list.get(position).getPortrait())
                    .placeholder(R.drawable.imageholder)
                    .override(200, 200)
                    .into(((ViewHolder1) holder).portrait);
            ((ViewHolder1) holder).message.setText(list.get(position).getContent());
            ((ViewHolder1) holder).name.setText("我");
            ((ViewHolder1) holder).time.setText(ParseUtils.CountTime(list.get(position).getTime()));
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
        @BindView(R.id.message)
        TextView message;

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
        @BindView(R.id.message)
        TextView message;

        ViewHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
