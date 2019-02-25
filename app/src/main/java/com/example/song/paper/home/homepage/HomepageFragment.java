package com.example.song.paper.home.homepage;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.song.paper.R;
import com.example.song.paper.base.BaseFragment;

public class HomepageFragment extends BaseFragment<HomePagePresenter> {

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        return view;
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

}
