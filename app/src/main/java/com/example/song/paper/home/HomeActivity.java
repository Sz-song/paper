package com.example.song.paper.home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.song.paper.R;
import com.example.song.paper.home.homepage.HomepageFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeActivity extends AppCompatActivity {

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
    private HomepageFragment homepageFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        homepageFragment = new HomepageFragment();
        fragmentTransaction.add(R.id.fragment_container, homepageFragment).commit();
        setPicDark();
        picHomepage.setImageResource(R.drawable.icon_homepage_light);
    }

    private void setPicDark() {
        picHomepage.setImageResource(R.drawable.icon_homepage_dark);
        picGuangchang.setImageResource(R.drawable.icon_guangchang_dark);
        picMessage.setImageResource(R.drawable.icon_message_dark);
        picMe.setImageResource(R.drawable.icon_me_dark);
    }

    @OnClick({R.id.homepage, R.id.guangchang, R.id.fabu, R.id.message, R.id.me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homepage:
                Toast.makeText(HomeActivity.this, "首页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.guangchang:
                Toast.makeText(HomeActivity.this, "广场", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fabu:
                Toast.makeText(HomeActivity.this, "发布", Toast.LENGTH_SHORT).show();
                break;
            case R.id.message:
                Toast.makeText(HomeActivity.this, "消息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.me:
                Toast.makeText(HomeActivity.this, "我的", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

