package com.example.song.paper.search;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.global.HttpService;
import com.example.song.paper.utils.HttpServiceInstance;

import java.util.List;

import io.reactivex.Observable;

public class SearchModel implements SearchConstract.ISearchModel {
    private HttpService httpService;
    SearchModel(){httpService = HttpServiceInstance.getInstance();}
    @Override
    public Observable<BaseResponse<List<AuctionBean>>> getSearchData(String useraccountid, String query) {
        return null;//Todo
    }
}
