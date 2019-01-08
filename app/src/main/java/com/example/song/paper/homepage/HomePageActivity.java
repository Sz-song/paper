package com.example.song.paper.homepage;
import com.example.song.paper.R;
import com.example.song.paper.base.BaseActivity;

public class HomePageActivity extends BaseActivity<HomePagePresenter> implements HomePageConstract.IHomePageView {

    @Override
    protected int getLayout() {
        return R.layout.activity_home_page;
    }

    @Override
    protected HomePagePresenter initPresent() {
        return null;
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onEventDestroy() {

    }
}
