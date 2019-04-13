package com.example.song.paper.release.release_dynamic;

import android.content.Context;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.common.DynamicBean;
import com.example.song.paper.utils.ExceptionHandler;

import java.util.List;

import io.reactivex.Observable;

/**
 * User: song
 * Date: 2019/4/13
 * Time: 14:26
 */
public interface ReleaseDynamicConstract {
    interface IReleaseDynamicModel{
        Observable<BaseResponse<Boolean>> ReleaseAuction(DynamicBean bean);
    }

    interface IReleaseDynamicView{
        void ReleaseAuctionSuccess(Boolean b);
        void ReleaseAuctionFail(ExceptionHandler.ResponeThrowable e);
    }

    interface IReleaseDynamicPresenter{
        void ReleaseAuction(DynamicBean bean);
    }
}
