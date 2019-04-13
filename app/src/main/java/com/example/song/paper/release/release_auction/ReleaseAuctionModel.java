package com.example.song.paper.release.release_auction;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.common.AuctionBean;

import io.reactivex.Observable;

/**
 * User: song
 * Date: 2019/4/13
 * Time: 14:31
 */
public class ReleaseAuctionModel implements ReleaseAuctionConstract.IReleaseDynamicModel {

    @Override
    public Observable<BaseResponse<Boolean>> ReleaseDynamic(AuctionBean bean) {
        return null;
    }
}
