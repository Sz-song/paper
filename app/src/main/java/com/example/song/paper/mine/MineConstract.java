package com.example.song.paper.mine;

import com.example.song.paper.base.BaseResponse;

import io.reactivex.Observable;

public interface MineConstract {
    interface IMineModel{
        Observable<BaseResponse<MineBean>> getMineData(String useraccountid);
    }

    interface IMineView{
        void setMineData(MineBean bean,boolean success);
    }

    interface IMinePresenter{
        void getMineData(String uid);
    }
}
