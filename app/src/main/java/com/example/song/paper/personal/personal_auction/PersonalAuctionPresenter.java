package com.example.song.paper.personal.personal_auction;

import android.content.Context;

import com.example.song.paper.base.BaseObserver;
import com.example.song.paper.base.BasePresenter;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.HttpServiceInstance;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PersonalAuctionPresenter extends BasePresenter<PersonalAuctionConstract.IPersonalAuctionView> implements PersonalAuctionConstract.IPersonalAuctionPresenter {
    private PersonalAuctionConstract.IPersonalAuctionModel model;

    public PersonalAuctionPresenter() {
        model=new PersonalAuctionModel();
    }

    @Override
    public void initList(String useraccountid,String userid, int page) {
        model.initList(useraccountid,userid,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<List<AuctionBean>>())
                .subscribe(new BaseObserver<List<AuctionBean>>() {
                    @Override
                    public void onNext(List<AuctionBean> auctionBeans) {
                        if(view!=null){
                            view.initListSuccess(auctionBeans);
                        }
                    }
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {
                        if(view!=null){
                            view.initListFail(e);
                        }
                    }
                });
    }
}
