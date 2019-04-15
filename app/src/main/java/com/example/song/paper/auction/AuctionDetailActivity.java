package com.example.song.paper.auction;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.base.BaseActivity;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.common.LoadingDialog;
import com.example.song.paper.common.ViewPagerAdapter;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.L;
import com.example.song.paper.utils.Sp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * User: song
 * Date: 2019/4/14
 * Time: 22:21
 */
public class AuctionDetailActivity extends BaseActivity<AuctionDetailPresenter> implements AuctionDetailConstract.IAuctionDetailView {

    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.img_num)
    TextView imgNum;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collasping_toorbar)
    CollapsingToolbarLayout collaspingToorbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.im_msg_recyclerview)
    RecyclerView imMsgRecyclerview;
    @BindView(R.id.price_start)
    TextView priceStart;
    @BindView(R.id.price_now)
    TextView priceNow;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.relat)
    CoordinatorLayout relat;
    private String id;
    private LoadingDialog dialog;
    private ViewPagerAdapter pagerAdapter;
    @Override
    protected int getLayout() {
        return R.layout.activity_auction_detail;
    }

    @Override
    protected AuctionDetailPresenter initPresent() {
        return new AuctionDetailPresenter();
    }

    @Override
    protected void initEvent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_black);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        dialog=new LoadingDialog(this);
        id=getIntent().getStringExtra("id");
        dialog.show();
        presenter.getAuctionDetailData(Sp.getString(this,AppConstant.UID),id);
    }

    @Override
    public void getAuctionDetailDataSuccess(AuctionBean bean) {
        dialog.dismiss();
        pagerAdapter = new ViewPagerAdapter(this, bean.getImages());
        viewpage.setAdapter(pagerAdapter);
        name.setText(bean.getName());
        time.setText(getTime(bean.getTime_start(),bean.getTime_end(),bean.getTime_now()));
        imgNum.setText("1/"+bean.getImages().size());
        priceStart.setText("起拍价:¥"+bean.getPrice_start());
        priceNow.setText("当前价:¥"+bean.getPrice_now());
        viewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {}
            @Override
            public void onPageSelected(int position) {
                int i = position + 1;
                imgNum.setText(i + "/" + bean.getImages().size());
            }
            @Override
            public void onPageScrollStateChanged(int i) {}
        });

    }

    @Override
    public void getAuctionDetailDataFail(ExceptionHandler.ResponeThrowable e) {
        dialog.dismiss();
        L.e(e.status+"  "+e.message);
        Toast.makeText(this, "获取数据失败", Toast.LENGTH_SHORT).show();
    }

    private String getTime(long time_start, long time_end,long time_now) {
        if (time_start > time_now) {
            return ((time_start ) - time_now) / (60 * 60) + "小时" + ((time_start - time_now) % (60 * 60)) / (60) + "分后开始";
        } else if (time_start < time_now && time_end > time_now) {
            return ((time_end ) - time_now) / (60 * 60) + "小时" + ((time_end  - time_now) % (60 * 60)) / (60) + "分后结束";
        } else {
            return "拍卖已结束";
        }
    }



    @OnClick(R.id.submit)
    public void onViewClicked() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

}
