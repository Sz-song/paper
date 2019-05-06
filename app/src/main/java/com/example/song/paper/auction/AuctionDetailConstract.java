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
        Observable<BaseResponse<List<AuctionRecordBean>>> getAuctionRecordData(String id);
        Observable<BaseResponse<Boolean>> AuctionOffer(String useraccountid, String id,String userid);
        Observable<BaseResponse<List<AuctionRecordBean>>> getAuctionRecordLate(String auction_id,String time);
    }

    interface IAuctionDetailView{
        void getAuctionDetailDataSuccess(AuctionBean bean);
        void getAuctionDetailDataFail(ExceptionHandler.ResponeThrowable e);

        void getAuctionRecordDataSuccess(List<AuctionRecordBean> beans);
        void getAuctionRecordDataFail(ExceptionHandler.ResponeThrowable e);

        void AuctionOfferSuccess(Boolean b);
        void AuctionOfferFail(ExceptionHandler.ResponeThrowable e);

        void getAuctionRecordLateSuccess(List<AuctionRecordBean> beans);
        void getAuctionRecordLateFail(ExceptionHandler.ResponeThrowable e);
    }

    interface IAuctionDetailPresenter{
        void getAuctionDetailData(String useraccountid, String id);
        void getAuctionRecordData(String id);
        void AuctionOffer(String useraccountid, String id,String userid);
        void getAuctionRecordLate(String auction_id,String time);
    }
}
