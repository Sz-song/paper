package com.example.song.paper.personal.personal_dynamic;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.common.DynamicBean;
import com.example.song.paper.utils.ExceptionHandler;

import java.util.List;

import io.reactivex.Observable;

public interface PersonalDynamicConstract {
    interface IPersonalDynamicModel{
        Observable<BaseResponse<List<DynamicBean>>> initList(String useraccountid, int page);
    }

    interface IPersonalDynamicView{
        void initListSuccess(List<DynamicBean> dynamicBeans);
        void initListFail(ExceptionHandler.ResponeThrowable e);
    }

    interface IPersonalDynamicPresenter{
        void initList(String useraccountid, int page);
    }
}
