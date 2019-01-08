package com.example.song.paper.register;

import com.example.song.paper.base.BaseResponse;

import io.reactivex.Observable;

public class RegisterModel implements RegisterContract.IRegisterModel {

    @Override
    public Observable<BaseResponse<String>> register(String username, String password) {
        return null;
    }
}
