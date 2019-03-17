package com.example.song.paper.register;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.utils.ExceptionHandler;

import io.reactivex.Observable;

public interface RegisterContract {
    interface IRegisterModel {
        Observable<BaseResponse<String>> register(String username, String password,String validcode);
        Observable<BaseResponse<String>> getCode(String username);
    }
    interface IRegisterView {
        void registerSuccess();
        void registerFail(ExceptionHandler.ResponeThrowable e);
        void getCodeSuccess();
        void getCodeFail(ExceptionHandler.ResponeThrowable e);
    }
    interface IRegisterPresenter {
        void register(String username, String password,String validcode);
        void getCode(String username);
    }

}
