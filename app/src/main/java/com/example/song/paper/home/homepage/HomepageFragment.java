package com.example.song.paper.home.homepage;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.song.paper.R;
import com.example.song.paper.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomepageFragment extends BaseFragment<HomePagePresenter> {

    @BindView(R.id.portrait)
    CircleImageView portrait;
    @BindView(R.id.serach)
    TextView serach;
    @BindView(R.id.filtrate)
    ImageView filtrate;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawer)
    DrawerLayout drawer;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_homepage, container, false);
    }

    @Override
    protected HomePagePresenter initPresent() {
        return new HomePagePresenter();
    }

    @Override
    public void initEvent(View view) {

    }

    @Override
    public void initData() {

    }
    @OnClick({R.id.portrait, R.id.serach, R.id.filtrate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.portrait:
                drawer.openDrawer(GravityCompat.START);
                break;
            case R.id.serach:
                break;
            case R.id.filtrate:
                break;
        }
    }
}
