package com.example.song.paper.home.homepage;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.utils.ExceptionHandler;

import io.reactivex.Observable;

public interface HomePageConstract {
    interface IHomePageModel{
        Observable<BaseResponse<HomePageBean>>getHomePageData();
    }

    interface IHomePageView{
        void getHomePageDataSuccess(HomePageBean homePageBean);
        void getHomePageDataFail(ExceptionHandler.ResponeThrowable e);
    }

    interface IHomePagePresenter{
        void getHomePageData();
    }
}
