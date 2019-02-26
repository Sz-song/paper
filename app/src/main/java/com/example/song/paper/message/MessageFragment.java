package com.example.song.paper.message;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.song.paper.R;
import com.example.song.paper.base.BaseFragment;

public class MessageFragment extends BaseFragment<MessagePresenter> implements MessageConstract.IMessageView {
    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    protected MessagePresenter initPresent() {
        return new MessagePresenter();
    }

    @Override
    protected void initEvent(View view) {

    }

    @Override
    protected void initData() {

    }
}
