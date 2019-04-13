package com.example.song.paper.release.release_dynamic;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.example.song.paper.R;
import com.example.song.paper.base.BaseActivity;
import com.example.song.paper.utils.ExceptionHandler;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User: song
 * Date: 2019/4/13
 * Time: 14:41
 */
public class ReleaseDynamicActivity extends BaseActivity<ReleaseDynamicPresenter> implements ReleaseDynamicConstract.IReleaseDynamicView {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.text_num)
    TextView textNum;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected int getLayout() {
        return R.layout.release_dynamic;
    }

    @Override
    protected ReleaseDynamicPresenter initPresent() {
        return new ReleaseDynamicPresenter();
    }

    @Override
    protected void initEvent() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_black);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        title.setText("发布动态");
    }

    @Override
    public void ReleaseAuctionSuccess(Boolean b) {

    }

    @Override
    public void ReleaseAuctionFail(ExceptionHandler.ResponeThrowable e) {

    }
}
