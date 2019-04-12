package com.example.song.paper.personal.personal_auction;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.global.HttpService;
import com.example.song.paper.utils.HttpServiceInstance;

import java.util.List;

import io.reactivex.Observable;

public class PersonalAuctionModel implements PersonalAuctionConstract.IPersonalDynamicModel {
    private HttpService httpService;

    public PersonalAuctionModel() {
        httpService = HttpServiceInstance.getInstance();
    }

    @Override
    public Observable<BaseResponse<List<AuctionBean>>> initList(String useraccountid, int page) {
        return null;
    }
}
