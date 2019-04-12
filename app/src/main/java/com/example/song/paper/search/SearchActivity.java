package com.example.song.paper.search;

import com.example.song.paper.R;
import com.example.song.paper.base.BaseActivity;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.utils.ExceptionHandler;

import java.util.List;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchConstract.ISearchView {
    @Override
    protected int getLayout() {
        return R.layout.acticity_serach;
    }

    @Override
    protected SearchPresenter initPresent() {
        return new SearchPresenter();
    }

    @Override
    protected void initEvent() {
        //Todo
    }

    @Override
    public void getSearchDataSuccess(List<AuctionBean> beans) {

    }

    @Override
    public void getSearchDataFail(ExceptionHandler.ResponeThrowable e) {

    }
}
