package com.example.song.paper.fansandfocus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.song.paper.R;
import com.example.song.paper.base.BaseFragment;
import com.example.song.paper.utils.ExceptionHandler;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * User: song
 * Date: 2019/4/10
 * Time: 21:45
 */
public class FansAndFocusFragment extends BaseFragment<FansAndFocusPresenter> implements FansAndFocusConstract.IFansAndFocusView {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.nodata_img)
    ImageView nodataImg;
    @BindView(R.id.nodata)
    TextView nodata;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private String useraccountid;
    private int page;
    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.common_fragment, container, false);
    }

    @Override
    protected FansAndFocusPresenter initPresent() {
        return new FansAndFocusPresenter();
    }

    @Override
    protected void initEvent(View view) {

    }

    @Override
    protected void initData() {
        presenter.initList(useraccountid,page);
    }

    @Override
    public void initListSuccess(List<FansAndFocusBean> Beans) {

    }

    @Override
    public void initListFail(ExceptionHandler.ResponeThrowable e) {

    }

}
