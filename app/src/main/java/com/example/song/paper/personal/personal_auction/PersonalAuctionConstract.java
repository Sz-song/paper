package com.example.song.paper.personal.personal_auction;


import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.utils.ExceptionHandler;

import java.util.List;

import io.reactivex.Observable;

public interface PersonalAuctionConstract {
    interface IPersonalAuctionModel{
        Observable<BaseResponse<List<AuctionBean>>> initList(String useraccountid,String userid, int page);
    }

    interface IPersonalAuctionView{
        void initListSuccess(List<AuctionBean> dynamicBeans);
        void initListFail(ExceptionHandler.ResponeThrowable e);
    }

    interface IPersonalAuctionPresenter{
        void initList(String useraccountid,String userid, int page);
    }
}
