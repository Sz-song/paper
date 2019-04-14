package com.example.song.paper.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.global.GlideApp;
import java.util.List;


/**
 * User: song
 * Date: 2019/4/14
 * Time: 23:04
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<String> data;
    private Context context;


    public ViewPagerAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.imageview, container, false);
        ViewHolder holder = new ViewHolder(view);
        view.setTag(holder);
        GlideApp.with(context)
                .load(AppConstant.Base_Url + data.get(position))
                .placeholder(R.drawable.imageholder)
                .override(400, 400)
                .into(holder.imageView);
        container.addView(view);
        return view;

    }

    static class ViewHolder {
        ImageView imageView;
        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.image);
        }
    }
}
