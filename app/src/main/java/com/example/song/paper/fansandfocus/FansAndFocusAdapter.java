package com.example.song.paper.fansandfocus;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.common.OnPositionClickListener;
import com.example.song.paper.global.GlideApp;
import com.example.song.paper.personal.personal_center.PersonalCenterActivity;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class FansAndFocusAdapter extends RecyclerView.Adapter<FansAndFocusAdapter.ViewHolder> {
    private Context context;
    private List<FansAndFocusBean> list;
    private OnPositionClickListener focusClickListener;

    public void setFocusClickListener(OnPositionClickListener focusClickListener) {
        this.focusClickListener = focusClickListener;
    }

    public FansAndFocusAdapter(Context context, List<FansAndFocusBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.cell_focusandfans, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        GlideApp.with(context)
                .load(AppConstant.Base_Url+list.get(position).getPortrait())
                .placeholder(R.drawable.imageholder)
                .override(100,100)
                .into(holder.image);
        holder.name.setText(list.get(position).getName());
        holder.txt.setText(list.get(position).getTxt());
        holder.fansNum.setText("粉丝:"+list.get(position).getFans_num());
        if(list.get(position).getIsfocus()==0){
            holder.isfocus.setText("✚ 关注");
            holder.isfocus.setActivated(true);
            holder.isfocus.setTextColor(context.getResources().getColor(R.color.gold));
        } else if(list.get(position).getIsfocus()==1){
            holder.isfocus.setText("✔ 已关注");
            holder.isfocus.setActivated(false);
            holder.isfocus.setTextColor(context.getResources().getColor(R.color.text_black));
        }
        holder.isfocus.setOnClickListener(v -> {
         focusClickListener.callback(position);
        });
        holder.item.setOnClickListener(view ->{
            Intent intent=new Intent(context,PersonalCenterActivity.class);
            intent.putExtra("userid",list.get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        CircleImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.txt)
        TextView txt;
        @BindView(R.id.isfocus)
        TextView isfocus;
        @BindView(R.id.fans_num)
        TextView fansNum;
        @BindView(R.id.item)
        RelativeLayout item;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
