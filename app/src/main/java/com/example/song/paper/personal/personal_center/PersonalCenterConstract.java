package com.example.song.paper.personal.personal_center;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.utils.ExceptionHandler;

import io.reactivex.Observable;

public interface PersonalCenterConstract {
    interface IPersonalCenterModel{
        Observable<BaseResponse<PersonCenterBean >> getPersonalCenterData(String useraccountid,String userid);
    }

    interface IPersonalCenterView{
        void getPersonalCenterDataSuccess(PersonCenterBean bean);
        void getPersonalCenterDataFail(ExceptionHandler.ResponeThrowable e);
    }

    interface IPersonalCenterPresenter{
        void getPersonalCenterData(String useraccountid,String userid);
    }
}
