package com.example.song.paper.search;

import com.example.song.paper.base.BaseActivity;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.utils.ExceptionHandler;

import java.util.List;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchConstract.ISearchView {
    @Override
    protected int getLayout() {
        return 0;//Todo
    }

    @Override
    protected SearchPresenter initPresent() {
        return null;
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void getSearchDataSuccess(List<AuctionBean> beans) {

    }

    @Override
    public void getSearchDataFail(ExceptionHandler.ResponeThrowable e) {

    }
}
