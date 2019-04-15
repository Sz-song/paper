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
        Observable<BaseResponse<Boolean>> ReleaseAuction(String useraccountid,String name,String time_start,String time_end,String price,List<String> list);
        Observable<BaseResponse<List<String>>> UploadImage(List<File> images);
    }

    interface IReleaseDynamicView{
        void ReleaseAuctionSuccess(Boolean b);
        void ReleaseAuctionFail(ExceptionHandler.ResponeThrowable e);

        void UploadImageSuccess(List<String> images);
        void UploadImageFail(ExceptionHandler.ResponeThrowable e);
    }

    interface IReleaseDynamicPresenter{
        void ReleaseAuction(String useraccountid,String name,String time_start,String time_end,String price,List<String> list);
        void UploadImage(List<File> images);
    }
}
