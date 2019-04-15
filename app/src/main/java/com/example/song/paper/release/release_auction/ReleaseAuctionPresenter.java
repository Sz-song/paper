package com.example.song.paper.release.release_auction;

import com.example.song.paper.base.BaseObserver;
import com.example.song.paper.base.BasePresenter;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.HttpServiceInstance;

import java.io.File;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * User: song
 * Date: 2019/4/13
 * Time: 14:30
 */
public class ReleaseAuctionPresenter extends BasePresenter<ReleaseAuctionConstract.IReleaseDynamicView> implements ReleaseAuctionConstract.IReleaseDynamicPresenter {
    private ReleaseAuctionConstract.IReleaseDynamicModel model;

    public ReleaseAuctionPresenter() {
        model=new ReleaseAuctionModel();
    }

    @Override
    public void ReleaseAuction(String useraccountid,String name,String time_start,String time_end,String price,List<String> list) {
        model.ReleaseAuction(useraccountid,name,time_start,time_end,price,list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<Boolean>())
                .subscribe(new BaseObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if(view!=null){
                            view.ReleaseAuctionSuccess(aBoolean);
                        }
                    }
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {
                        if(view!=null){
                            view.ReleaseAuctionFail(e);
                        }
                    }
                });
    }

    @Override
    public void UploadImage(List<File> images) {
        model.UploadImage(images)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<List<String>>())
                .subscribe(new BaseObserver<List<String>>() {
                    @Override
                    public void onNext(List<String> strings) {
                        if(view!=null){
                            view.UploadImageSuccess(strings);
                        }
                    }
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {
                        if(view!=null){
                            view.UploadImageFail(e);
                        }
                    }
                });
    }
}
