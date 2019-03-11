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
import com.example.song.paper.message.MessageFragment;
import com.example.song.paper.mine.MineFragment;
import com.example.song.paper.square.SquareFragment;

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
    private SquareFragment squareFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
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
        picHomepage.setImageResource(R.drawable.ic_homepage_light);
    }

    @OnClick({R.id.homepage, R.id.guangchang, R.id.fabu, R.id.message, R.id.me})
    public void onViewClicked(View view) {
        changeFragment(view);
    }

    private void setPicDark() {
        picHomepage.setImageResource(R.drawable.ic_homepage_dark);
        picGuangchang.setImageResource(R.drawable.ic_guangchang_dark);
        picMessage.setImageResource(R.drawable.ic_message_dark);
        picMe.setImageResource(R.drawable.ic_me_dark);
    }

    private void changeFragment(View view){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (homepageFragment != null && homepageFragment.isVisible()) {
            fragmentTransaction.hide(homepageFragment);
        }
        if (squareFragment != null && squareFragment .isVisible()) {
            fragmentTransaction.hide(squareFragment );
        }
        if (messageFragment != null && messageFragment.isVisible()) {
            fragmentTransaction.hide(messageFragment);
        }
        if (mineFragment != null && mineFragment .isVisible()) {
            fragmentTransaction.hide(mineFragment);
        }
        switch (view.getId()) {
            case R.id.homepage:
                setPicDark();
                picHomepage.setImageResource(R.drawable.ic_homepage_light);
                if (homepageFragment == null) {
                    homepageFragment = new HomepageFragment();
                    fragmentTransaction.add(R.id.fragment_container, homepageFragment);
                } else fragmentTransaction.show(homepageFragment);
                fragmentTransaction.commit();
                break;
            case R.id.guangchang:
                setPicDark();
                picGuangchang.setImageResource(R.drawable.ic_guangchang_light);
                if (squareFragment == null) {
                    squareFragment  = new SquareFragment();
                    fragmentTransaction.add(R.id.fragment_container, squareFragment );
                } else fragmentTransaction.show(squareFragment);
                fragmentTransaction.commit();
                break;
            case R.id.fabu:
                Toast.makeText(HomeActivity.this, "发布", Toast.LENGTH_SHORT).show();
                break;
            case R.id.message:
                setPicDark();
                picMessage.setImageResource(R.drawable.ic_message_light);
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    fragmentTransaction.add(R.id.fragment_container, messageFragment);
                } else fragmentTransaction.show(messageFragment);
                fragmentTransaction.commit();
                break;
            case R.id.me:
                setPicDark();
                picMe.setImageResource(R.drawable.ic_me_light);
                if (mineFragment== null) {
                    mineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.fragment_container, mineFragment);
                } else fragmentTransaction.show(mineFragment);
                fragmentTransaction.commit();
                break;
        }
    }
}

