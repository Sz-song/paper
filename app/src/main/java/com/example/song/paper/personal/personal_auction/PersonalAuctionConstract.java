package com.example.song.paper.personal.personal_auction;

import android.content.Context;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.utils.ExceptionHandler;

import java.util.List;

import io.reactivex.Observable;

public interface PersonalAuctionConstract {
    interface IPersonalDynamicModel{
        Observable<BaseResponse<List<AuctionBean>>> initList(String useraccountid, int page);
    }

    interface IPersonalDynamicView{
        void initListSuccess(List<AuctionBean> dynamicBeans);
        void initListFail(ExceptionHandler.ResponeThrowable e);
    }

    interface IPersonalDynamicPresenter{
        void initList(Context context, int page);
    }
}
