package com.example.song.paper.register;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.utils.ExceptionHandler;

import io.reactivex.Observable;

public interface RegisterContract {
    interface IRegisterModel {
        Observable<BaseResponse<Boolean>> register(String username, String password,String code,String name);
        Observable<BaseResponse<Boolean>> getCode(String username);
    }
    interface IRegisterView {
        void registerSuccess();
        void registerFail(ExceptionHandler.ResponeThrowable e);
        void getCodeSuccess();
        void getCodeFail(ExceptionHandler.ResponeThrowable e);
    }
    interface IRegisterPresenter {
        void register(String username, String password,String code,String name);
        void getCode(String username);
    }

}
