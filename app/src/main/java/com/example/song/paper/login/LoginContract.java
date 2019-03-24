package com.example.song.paper.login;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.utils.ExceptionHandler;

import io.reactivex.Observable;

public interface LoginContract {

    interface ILoginModel {
        Observable<BaseResponse<UserBean>> login(String username, String password);
    }


    interface ILoginPresenter {
        void login(String username, String password);
    }


    interface ILoginView {
        void loginSuccess(UserBean bean);
        void loginFail(ExceptionHandler.ResponeThrowable e);
    }
}
