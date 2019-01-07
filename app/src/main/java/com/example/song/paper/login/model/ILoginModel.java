package com.example.song.paper.login.model;

import com.example.song.paper.common.base.BaseResponse;

import io.reactivex.Observable;

public interface ILoginModel {
    Observable<BaseResponse<String>> login(String username, String password);
    void register(String username,String password);
}
