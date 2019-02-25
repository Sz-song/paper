package com.example.song.paper.square;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.song.paper.R;
import com.example.song.paper.base.BaseFragment;

public class SquareFragment extends BaseFragment<SquarePresenter> implements SquareConstract.ISquareView {
    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_square, container, false);
        return view;
    }

    @Override
    protected SquarePresenter initPresent() {
        return null;
    }

    @Override
    protected void initEvent(View view) {

    }

    @Override
    protected void initData() {

    }
}
