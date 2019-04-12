package com.example.song.paper.personal.personal_center;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.example.song.paper.R;
import com.example.song.paper.base.BaseActivity;
import com.example.song.paper.utils.ExceptionHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalCenterActivity extends BaseActivity<PersonalCenterPresenter> implements PersonalCenterConstract.IPersonalCenterView {

    @BindView(R.id.background)
    ImageView background;
    @BindView(R.id.title_image)
    CircleImageView titleImage;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.focus_num)
    TextView focusNum;
    @BindView(R.id.fans_num)
    TextView fansNum;
    @BindView(R.id.introduce)
    TextView introduce;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.coll)
    CollapsingToolbarLayout coll;
    @BindView(R.id.tabLayout)
    XTabLayout tabLayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.focus)
    TextView focus;
    @BindView(R.id.contact)
    TextView contact;
    @BindView(R.id.bottom_relat)
    LinearLayout bottomRelat;
    private String userid;
    @Override
    protected int getLayout() {
        return R.layout.activity_personal_center;
    }

    @Override
    protected PersonalCenterPresenter initPresent() {
        return new PersonalCenterPresenter();
    }

    @Override
    protected void initEvent() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_black);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
    }

    @Override
    public void getPersonalCenterDataSuccess(PersonCenterBean bean) {

    }

    @Override
    public void getPersonalCenterDataFail(ExceptionHandler.ResponeThrowable e) {

    }

    @OnClick({R.id.title_image, R.id.focus, R.id.contact})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_image:
                break;
            case R.id.focus:
                break;
            case R.id.contact:
                break;
        }
    }
}
