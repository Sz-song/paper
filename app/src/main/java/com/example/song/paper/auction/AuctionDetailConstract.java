package com.example.song.paper.auction;

import android.content.Context;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.common.DynamicBean;
import com.example.song.paper.utils.ExceptionHandler;

import java.util.List;

import io.reactivex.Observable;

/**
 * User: song
 * Date: 2019/4/14
 * Time: 22:16
 */
public interface AuctionDetailConstract {
    interface IAuctionDetailModel{
        Observable<BaseResponse<AuctionBean>> getAuctionDetailData(String useraccountid, String id);
    }

    interface IAuctionDetailView{
        void getAuctionDetailDataSuccess(AuctionBean bean);
        void getAuctionDetailDataFail(ExceptionHandler.ResponeThrowable e);
    }

    interface IAuctionDetailPresenter{
        void getAuctionDetailData(String useraccountid, String id);
    }
}
