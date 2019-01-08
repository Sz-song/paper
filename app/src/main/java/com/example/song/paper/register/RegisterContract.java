package com.example.song.paper.register;

import com.example.song.paper.base.BaseResponse;

import io.reactivex.Observable;

public interface RegisterContract {
    interface IRegisterModel {
        Observable<BaseResponse<String>> register(String username, String password);
    }


    interface IRegisterPresenter {
        void register(String username, String password);
        void getCode(String username);
        void forget_pwd(String username,String password,String code);
    }


    interface IRegisterView {
        void showToast(String msg);
        void jumpActivity();
    }
}
