package com.example.song.paper.auction;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.song.paper.R;
import com.example.song.paper.base.BaseActivity;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.utils.ExceptionHandler;

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
    @BindView(R.id.price_add)
    TextView priceAdd;
    @BindView(R.id.my_price)
    TextView myPrice;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.relat)
    CoordinatorLayout relat;

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
        ButterKnife.bind(this);
    }

    @Override
    public void getAuctionDetailDataSuccess(AuctionBean bean) {

    }

    @Override
    public void getAuctionDetailDataFail(ExceptionHandler.ResponeThrowable e) {

    }

    @OnClick(R.id.submit)
    public void onViewClicked() {
    }
}
