package com.example.song.paper.auction;

import com.example.song.paper.base.BaseObserver;
import com.example.song.paper.base.BasePresenter;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.HttpServiceInstance;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * User: song
 * Date: 2019/4/14
 * Time: 22:22
 */
public class AuctionDetailPresenter extends BasePresenter<AuctionDetailConstract.IAuctionDetailView> implements AuctionDetailConstract.IAuctionDetailPresenter {
    private AuctionDetailConstract.IAuctionDetailModel model;

    public AuctionDetailPresenter() {
        model=new AuctionDetailModel();
    }

    @Override
    public void getAuctionDetailData(String useraccountid, String id) {
        model.getAuctionDetailData(useraccountid,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<AuctionBean>())
                .subscribe(new BaseObserver<AuctionBean>() {
                    @Override
                    public void onNext(AuctionBean auctionBean) {
                        if(view!=null){
                            view.getAuctionDetailDataSuccess(auctionBean);
                        }
                    }
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {
                        if(view!=null){
                            view.getAuctionDetailDataFail(e);
                        }
                    }
                });
    }

    @Override
    public void getAuctionRecordData(String id) {
        model.getAuctionRecordData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<List<AuctionRecordBean>>())
                .subscribe(new BaseObserver<List<AuctionRecordBean>>() {
                    @Override
                    public void onNext(List<AuctionRecordBean> beans) {
                        if(view!=null){
                            view.getAuctionRecordDataSuccess(beans);
                        }
                    }
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {
                        if(view!=null){
                            view.getAuctionRecordDataFail(e);
                        }
                    }
                });
    }
}
