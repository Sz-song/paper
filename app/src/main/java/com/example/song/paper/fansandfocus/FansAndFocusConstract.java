package com.example.song.paper.fansandfocus;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.utils.ExceptionHandler;

import java.util.List;

import io.reactivex.Observable;

/**
 * User: song
 * Date: 2019/4/10
 * Time: 20:52
 */
public interface FansAndFocusConstract {
    interface IFanAndsFocusModel {
        Observable<BaseResponse<List<FansAndFocusBean>>> initList(String useraccountid,int type,  int page);
    }

    interface IFansAndFocusView {
        void initListSuccess(List<FansAndFocusBean> Beans);
        void initListFail(ExceptionHandler.ResponeThrowable e);
    }

    interface IFansAndFocusPresenter {
        void initList(String useraccountid,int type, int page);
    }
}
