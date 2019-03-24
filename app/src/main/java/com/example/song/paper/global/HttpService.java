package com.example.song.paper.global;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.home.homepage.HomePageBean;
import com.example.song.paper.login.UserBean;
import com.example.song.paper.message.MessageBean;
import com.example.song.paper.mine.MineBean;
import com.example.song.paper.square.DynamicBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HttpService {
    //用户注册
    @POST("app_api/register.php")
    Observable<BaseResponse<String>> register(@Body RequestBody body);
    //用户登录
    @POST("app_api/login.php")
    Observable<BaseResponse<UserBean>> login(@Body RequestBody body);
    //获取首页数据
    @POST("app_api/homepage.php")
    Observable<BaseResponse<HomePageBean>> getHomePageData(@Body RequestBody body);
    //获取广场数据
    @POST("app_api/square.php")
    Observable<BaseResponse<List<DynamicBean>>> getSquareData(@Body RequestBody body);
    //获取消息列表数据
    @POST("app_api/message.php")
    Observable<BaseResponse<List<MessageBean>>> getMessageData(@Body RequestBody body);
    //获取我的数据
    @POST("app_api/mine.php")
    Observable<BaseResponse<MineBean>> getMineData(@Body RequestBody body);
}
