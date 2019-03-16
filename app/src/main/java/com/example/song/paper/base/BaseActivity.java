package com.example.song.paper.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected   P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        presenter =initPresent();
        presenter.attachView(this);
        initEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    protected abstract  int getLayout();
    protected abstract P initPresent();
    protected abstract void initEvent();
}
