package com.example.song.paper.home;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.song.paper.R;
import com.example.song.paper.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomePageActivity extends BaseActivity<HomePagePresenter> implements HomePageConstract.IHomePageView {

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.pic_homepage)
    ImageView picHomepage;
    @BindView(R.id.homepage)
    RelativeLayout homepage;
    @BindView(R.id.pic_guangchang)
    ImageView picGuangchang;
    @BindView(R.id.guangchang)
    RelativeLayout guangchang;
    @BindView(R.id.pic_fabu)
    ImageView picFabu;
    @BindView(R.id.fabu)
    RelativeLayout fabu;
    @BindView(R.id.pic_message)
    ImageView picMessage;
    @BindView(R.id.message)
    RelativeLayout message;
    @BindView(R.id.pic_me)
    ImageView picMe;
    @BindView(R.id.me)
    RelativeLayout me;

    @Override
    protected int getLayout() {
        return R.layout.activity_home_page;
    }

    @Override
    protected HomePagePresenter initPresent() {
        return new HomePagePresenter();
    }

    @Override
    protected void initEvent() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onEventDestroy() {

    }

    @OnClick({R.id.homepage, R.id.guangchang, R.id.fabu, R.id.message, R.id.me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homepage:
                Toast.makeText(HomePageActivity.this, "首页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.guangchang:
                Toast.makeText(HomePageActivity.this, "广场", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fabu:
                Toast.makeText(HomePageActivity.this, "发布", Toast.LENGTH_SHORT).show();
                break;
            case R.id.message:
                Toast.makeText(HomePageActivity.this, "消息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.me:
                Toast.makeText(HomePageActivity.this, "我的", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

