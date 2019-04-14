package com.example.song.paper.release.release_auction;

import android.content.Context;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.utils.ExceptionHandler;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;

/**
 * User: song
 * Date: 2019/4/13
 * Time: 14:26
 */
public interface ReleaseAuctionConstract {
    interface IReleaseDynamicModel{
        Observable<BaseResponse<Boolean>> ReleaseDynamic(AuctionBean bean);
        Observable<BaseResponse<List<String>>> UploadImage(List<File> images);
    }

    interface IReleaseDynamicView{
        void ReleaseDynamicSuccess(Boolean b);
        void ReleaseDynamicFail(ExceptionHandler.ResponeThrowable e);

        void UploadImageSuccess(List<String> images);
        void UploadImageFail(ExceptionHandler.ResponeThrowable e);
    }

    interface IReleaseDynamicPresenter{
        void ReleaseDynamic(AuctionBean bean);
        void UploadImage(List<File> images);
    }
}
