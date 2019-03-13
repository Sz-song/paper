package com.example.song.paper.message;

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private Context context;
    private List<MessageBean> list;

    public MessageAdapter(Context context, List<MessageBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_message, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideApp.with(context)
                .load(AppConstant.Base_Url+list.get(position).getPortrait())
                .placeholder(R.drawable.imageholder)
                .override(100,100)
                .into(holder.portrait);
        holder.name.setText(list.get(position).getName());
        holder.time.setText(ParseUtils.CountTime(list.get(position).getTime()));
        holder.content.setText(list.get(position).getContent());
        holder.itemView.setOnClickListener(v -> {
            //TODO 跳转到聊天详情页
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.portrait)
        CircleImageView portrait;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.time)
        TextView time;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
