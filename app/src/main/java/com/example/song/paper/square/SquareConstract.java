package com.example.song.paper.square;

import android.content.Context;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.common.DynamicBean;
import com.example.song.paper.utils.ExceptionHandler;

import java.util.List;

import io.reactivex.Observable;

public interface SquareConstract {
    interface ISquareModel{
        Observable<BaseResponse<List<DynamicBean>>> getSquareData(String useraccountid, int page);
    }

    interface ISquareView{
        void initListSuccess(List<DynamicBean> dynamicBeans);
        void initListFail(ExceptionHandler.ResponeThrowable e);
    }

    interface ISquarePresenter{
        void initList(Context context,int page);
    }
}
