package com.example.song.paper.search;

import com.example.song.paper.base.BasePresenter;

public class SearchPresenter extends BasePresenter<SearchConstract.ISearchView> implements SearchConstract.ISearchPresenter {
    private SearchConstract.ISearchModel model;

    public SearchPresenter() {
        model=new SearchModel();
    }

    @Override
    public void getSearchData(String useraccountid, String query) {
        //Todo
    }
}
