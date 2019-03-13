package com.example.song.paper.global;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.message.MessageBean;
import com.example.song.paper.mine.MineBean;
import com.example.song.paper.square.DynamicBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HttpService {
    //用户登录
    @POST("app_api/login.php")
    Observable<BaseResponse<String>> login(@Body RequestBody body);
    //获取广场数据
    @POST("app_api/login.php")
    Observable<BaseResponse<List<DynamicBean>>> getSquareData(@Body RequestBody body);
    //获取消息列表数据
    @POST("app_api/login.php")
    Observable<BaseResponse<List<MessageBean>>> getMessageData(@Body RequestBody body);
    //获取我的数据
    @POST("app_api/login.php")
    Observable<BaseResponse<MineBean>> getMineData(@Body RequestBody body);
}
