package com.example.song.paper.release;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.song.paper.R;
import com.example.song.paper.release.release_auction.ReleaseAuctionActivity;
import com.example.song.paper.release.release_dynamic.ReleaseDynamicActivity;

/**
 * User: song
 * Date: 2019/4/13
 * Time: 14:49
 */
public class ReleasePopupwindow  extends PopupWindow {
    private View view;
    private LinearLayout release_auction,release_dynamic;
    public ReleasePopupwindow(Context context) {
        super(context);
        this.view = LayoutInflater.from(context).inflate(R.layout.popup_release, null);
        this.release_auction=view.findViewById(R.id.release_auction);
        this.release_dynamic=view.findViewById(R.id.release_dynamic);
        this.setOutsideTouchable(true);//外部点击消失
        this.setContentView(this.view);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x004000000);
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.dialogAnimStyle);
        WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
        lp.alpha = 0.7f; // 0.0-1.0
        ((Activity)context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity) context).getWindow().setAttributes(lp);
        this.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = ((Activity)context).getWindow().getAttributes();
            lp1.alpha = 1f; // 0.0-1.0
            ((Activity)context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            ((Activity)context).getWindow().setAttributes(lp1);
        });
        release_auction.setOnClickListener(v -> {
            Intent intent=new Intent(context,ReleaseAuctionActivity.class);
            context.startActivity(intent);
        });
        release_dynamic.setOnClickListener(v -> {
            Intent intent=new Intent(context,ReleaseDynamicActivity.class);
            context.startActivity(intent);
        });
    }


}