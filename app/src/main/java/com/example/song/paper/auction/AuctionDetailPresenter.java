package com.example.song.paper.auction;

import com.example.song.paper.base.BasePresenter;

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

    }
}
