package com.example.song.paper.mine;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.song.paper.R;
import com.example.song.paper.base.BaseFragment;

public class MineFragment extends BaseFragment<MinePresenter> implements MineConstract.IMineView {
    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    protected MinePresenter initPresent() {
        return new MinePresenter();
    }

    @Override
    protected void initEvent(View view) {

    }

    @Override
    protected void initData() {

    }
}
