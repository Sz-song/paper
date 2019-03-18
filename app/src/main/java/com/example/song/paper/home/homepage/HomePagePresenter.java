package com.example.song.paper.home.homepage;

import com.example.song.paper.base.BaseObserver;
import com.example.song.paper.base.BasePresenter;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.HttpServiceInstance;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomePagePresenter extends BasePresenter<HomePageConstract.IHomePageView> implements HomePageConstract.IHomePagePresenter {
    private HomePageConstract.IHomePageModel model;
    public HomePagePresenter() {this.model = new HomePageModel();}
    @Override
    public void getHomePageData() {
        model.getHomePageData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<HomePageBean>())
                .subscribe(new BaseObserver<HomePageBean>() {
                    @Override
                    public void onNext(HomePageBean homePageBean) {view.getHomePageDataSuccess(homePageBean); }
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {view.getHomePageDataFail(e);}
                });
    }
}
