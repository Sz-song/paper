package com.example.song.paper.auction;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.common.AuctionBean;

import io.reactivex.Observable;

/**
 * User: song
 * Date: 2019/4/14
 * Time: 22:22
 */
public class AuctionDetailModel implements AuctionDetailConstract.IAuctionDetailModel {
    @Override
    public Observable<BaseResponse<AuctionBean>> getAuctionDetailData(String useraccountid, String id) {
        return null;
    }
}
