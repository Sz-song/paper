package com.example.song.paper.mine;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.utils.ExceptionHandler;

import io.reactivex.Observable;

public interface MineConstract {
    interface IMineModel{
        Observable<BaseResponse<MineBean>> getMineData(String useraccountid);
    }

    interface IMineView{
        void getMineDataSuccess(MineBean bean);
        void getMineDataFail(ExceptionHandler.ResponeThrowable e);
    }

    interface IMinePresenter{
        void getMineData(String uid);
    }
}
