package com.example.song.paper.home.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.song.paper.R;
import com.example.song.paper.base.BaseFragment;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.search.SearchActivity;
import com.example.song.paper.utils.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomepageFragment extends BaseFragment<HomePagePresenter> implements HomePageConstract.IHomePageView {

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
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private List<AuctionBean> list;
    private List<String> bannerimages;
    private HomePageAdpter adpter;

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
        list = new ArrayList<>();
        bannerimages = new ArrayList<>();
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 2;
                } else return 1;
            }
        });
        recycleview.setLayoutManager(manager);
        adpter = new HomePageAdpter(getContext(), list, bannerimages);
        recycleview.setAdapter(adpter);
    }

    @Override
    public void initData() {
        swipe.setRefreshing(true);
        presenter.getHomePageData();
//        navigation.getHeaderView(R.id.nav_portrait).setBackgroundColor(getResources().getColor(R.color.gold));
    }
    @Override
    public void getHomePageDataSuccess(HomePageBean bean) {
        swipe.setRefreshing(false);
        bannerimages.addAll(bean.getBannerimages());
        list.addAll(bean.getList());
        adpter.notifyDataSetChanged();
    }
    @Override
    public void getHomePageDataFail(ExceptionHandler.ResponeThrowable e) {
        swipe.setRefreshing(false);
        Toast.makeText(getContext(), e.message, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.portrait, R.id.serach, R.id.filtrate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.portrait:
                drawer.openDrawer(GravityCompat.START);
                break;
            case R.id.serach:
                Intent intent=new Intent(getContext(),SearchActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.filtrate:
                //TODO
                break;
        }
    }
}
