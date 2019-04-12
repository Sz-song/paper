package com.example.song.paper.search;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.utils.ExceptionHandler;

import java.util.List;

import io.reactivex.Observable;

public interface SearchConstract {
    interface ISearchModel{
        Observable<BaseResponse<List<AuctionBean>>> getSearchData(String useraccountid, String query);
    }

    interface ISearchView{
        void getSearchDataSuccess(List<AuctionBean> beans);
        void getSearchDataFail(ExceptionHandler.ResponeThrowable e);
    }

    interface ISearchPresenter{
        void getSearchData(String useraccountid,String query);
    }
}
