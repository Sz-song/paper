package com.example.song.paper.home.homepage;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.base.BaseFragment;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.fansandfocus.FansAndFocusActivity;
import com.example.song.paper.global.GlideApp;
import com.example.song.paper.login.LoginActivity;
import com.example.song.paper.personal.personal_center.PersonalCenterActivity;
import com.example.song.paper.search.SearchActivity;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.Sp;

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
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    private CircleImageView nav_portrait;
    private TextView nav_name,nav_introduce;
    private ImageView nav_background;
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
        nav_portrait=navigation.getHeaderView(0).findViewById(R.id.nav_portrait);
        nav_name=navigation.getHeaderView(0).findViewById(R.id.nav_name);
        nav_introduce=navigation.getHeaderView(0).findViewById(R.id.nav_introduce);
        nav_background=navigation.getHeaderView(0).findViewById(R.id.nav_background);
        navigation.setNavigationItemSelectedListener(item -> {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.my_home:
                    intent=new Intent(getContext(),PersonalCenterActivity.class);
                    intent.putExtra("position",0);
                    intent.putExtra("userid",Sp.getString(getContext(),AppConstant.UID));
                    startActivity(intent);
                    break;
                case R.id.my_focus:
                    intent=new Intent(getContext(),FansAndFocusActivity.class);
                    intent.putExtra("position",0);
                    intent.putExtra("userid",Sp.getString(getContext(),AppConstant.UID));
                    startActivity(intent);
                    break;
                case R.id.my_fans:
                    intent=new Intent(getContext(),FansAndFocusActivity.class);
                    intent.putExtra("position",1);
                    intent.putExtra("userid",Sp.getString(getContext(),AppConstant.UID));
                    startActivity(intent);
                    break;
                case R.id.exit_login:
                    intent=new Intent(getActivity(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    break;
            }
            return true;
        });
    }

    @Override
    public void initData() {
        presenter.getHomePageData(Sp.getString(getContext(),AppConstant.UID));
    }
    @Override
    public void getHomePageDataSuccess(HomePageBean bean) {
        bannerimages.addAll(bean.getBannerimages());
        list.addAll(bean.getList());
        adpter.notifyDataSetChanged();
        GlideApp.with(getContext())
                .load(AppConstant.Base_Url+bean.getPortrait())
                .placeholder(R.drawable.imageholder)
                .override(100,100)
                .into(portrait);
        GlideApp.with(getContext())
                .load(AppConstant.Base_Url+bean.getPortrait())
                .placeholder(R.drawable.imageholder)
                .override(100,100)
                .into(nav_portrait);
        GlideApp.with(getContext())
                .load(R.drawable.background)
                .override(800,500)
                .into(nav_background);
        nav_name.setText(bean.getName());
        nav_introduce.setText("个人简介:"+bean.getTxt());

    }
    @Override
    public void getHomePageDataFail(ExceptionHandler.ResponeThrowable e) {
        Toast.makeText(getContext(), e.message, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.portrait, R.id.serach})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.portrait:
                drawer.openDrawer(GravityCompat.START);
                break;
            case R.id.serach:
                Intent intent=new Intent(getContext(),SearchActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }

}
