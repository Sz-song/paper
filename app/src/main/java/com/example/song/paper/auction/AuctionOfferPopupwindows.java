package com.example.song.paper.auction;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.song.paper.R;

public class AuctionOfferPopupwindows extends PopupWindow {
    private TextView priceNowTxt,priceNow,priceAdd,priceMy,reduce,add;
    private Button checkAdd;
    private Context context;
    private View view;
    private double currentPrice;
    private OnCheckAddClickListener offerListener;

    public AuctionOfferPopupwindows(final Context context,final double currentPrice) {
        this.context = context;
        this.currentPrice=currentPrice;
        this.view = LayoutInflater.from(context).inflate(R.layout.popup_auction, null);
        priceNowTxt=view.findViewById(R.id.price_now_txt);
        priceMy=view.findViewById(R.id.price_my);
        priceNow=view.findViewById(R.id.price_now);
        reduce=view.findViewById(R.id.reduce);
        add=view.findViewById(R.id.add);
        checkAdd=view.findViewById(R.id.check_add);
        priceNow.setText("¥"+currentPrice);
        priceMy.setText(currentPrice+"");
        this.setOutsideTouchable(true);//外部点击消失
        this.setContentView(this.view);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x004000000);
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.dialogAnimStyle);
        this.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = ((Activity)context).getWindow().getAttributes();
            lp1.alpha = 1f; // 0.0-1.0
            ((Activity)context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            ((Activity)context).getWindow().setAttributes(lp1);
        });
        WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
        lp.alpha = 0.7f; // 0.0-1.0
        ((Activity) context).getWindow().setAttributes(lp);
        reduce.setOnClickListener(view -> {
            this.currentPrice = this.currentPrice - 1;
            priceMy.setText(this.currentPrice+"");
        });
        add.setOnClickListener(view -> {
            this.currentPrice = this.currentPrice + 1;
            priceMy.setText(this.currentPrice+"");
        });
        checkAdd.setOnClickListener(v -> {
            offerListener.callback(currentPrice);
        });
    }

    public void setOfferListener(OnCheckAddClickListener offerListener) {
        this.offerListener = offerListener;
    }
}
