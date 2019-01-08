package com.example.song.paper.login;

import com.example.song.paper.base.BaseResponse;

import io.reactivex.Observable;

public interface LoginContract {

    interface ILoginModel {
        Observable<BaseResponse<String>> login(String username, String password);
        void register(String username, String password);
    }

    interface ILoginPresenter {
        void login(String username, String password);
        void register(String username, String password);
        void forget_pwd(String username);
        void getCode(String username);
    }

    interface ILoginView {
        void showToast(String msg);
        void jumpActivity();
    }
}
