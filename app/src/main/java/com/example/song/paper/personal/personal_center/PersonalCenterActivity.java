package com.example.song.paper.personal.personal_center;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidkun.xtablayout.XTabLayout;
import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.base.BaseActivity;
import com.example.song.paper.fansandfocus.FansAndFocusActivity;
import com.example.song.paper.global.GlideApp;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.L;
import com.example.song.paper.utils.Sp;

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
        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        PersonalFragmentAdapter adapter = new PersonalFragmentAdapter(getSupportFragmentManager(), userid);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        presenter.getPersonalCenterData(Sp.getString(this, AppConstant.UID), userid);
    }

    @Override
    public void getPersonalCenterDataSuccess(PersonCenterBean bean) {
        GlideApp.with(this)
                .load(AppConstant.Base_Url + bean.getPortrait())
                .override(100, 100)
                .placeholder(R.drawable.imageholder)
                .into(titleImage);
        GlideApp.with(this)
                .load(AppConstant.Base_Url + bean.getPortrait())
                .override(100, 100)
                .placeholder(R.drawable.imageholder)
                .into(background);
        name.setText(bean.getName());
        introduce.setText(bean.getIntroduce());
        fansNum.setText("粉丝:" + bean.getFans_num());
        focusNum.setText("关注:" + bean.getFocus_num());
        if (bean.isIsfocus()) {
            focus.setText("已关注");
        } else {
            focus.setText("＋关注");
        }
    }

    @Override
    public void getPersonalCenterDataFail(ExceptionHandler.ResponeThrowable e) {
        L.e(e.status + "   " + e.message);
        Toast.makeText(this, "获取个人信息失败", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.title_image, R.id.focus, R.id.contact,R.id.focus_num, R.id.fans_num})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.title_image:
                break;
            case R.id.focus:
                break;
            case R.id.contact:
                break;
            case R.id.focus_num:
                intent=new Intent(this,FansAndFocusActivity.class);
                intent.putExtra("position",0);
                intent.putExtra("userid",userid);
                startActivity(intent);
                break;
            case R.id.fans_num:
                intent=new Intent(this,FansAndFocusActivity.class);
                intent.putExtra("position",1);
                intent.putExtra("userid",userid);
                startActivity(intent);
                break;
        }
    }
}
