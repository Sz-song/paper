package com.example.song.paper.common.utils;

import com.example.song.paper.common.base.BaseResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HttpService {
    @POST("app_api/login.php")
    Observable<BaseResponse<String[]>> login(@Body RequestBody body);
}