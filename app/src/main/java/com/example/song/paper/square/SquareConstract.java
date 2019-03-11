package com.example.song.paper.square;

import android.content.Context;

import com.example.song.paper.base.BaseResponse;

import java.util.List;

import io.reactivex.Observable;

public interface SquareConstract {
    interface ISquareModel{
        Observable<BaseResponse<List<DynamicBean>>> getSquareData(String useraccountid, int page);
    }

    interface ISquareView{
        void updata(List<DynamicBean> dynamicBeans,boolean success);
    }

    interface ISquarePresenter{
        void initList(Context context,int page);
    }
}
