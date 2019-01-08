package com.example.song.paper.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected   P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mPresenter=initPresent();
        mPresenter.attachView(this);
        initEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onEventDestroy();
        mPresenter.detachView();
    }

    protected abstract  int getLayout();
    protected abstract P initPresent();
    protected abstract void initEvent();
    protected abstract void onEventDestroy();
}
