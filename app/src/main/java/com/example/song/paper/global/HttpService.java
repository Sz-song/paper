package com.example.song.paper.global;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.fansandfocus.FansAndFocusBean;
import com.example.song.paper.home.homepage.HomePageBean;
import com.example.song.paper.login.UserBean;
import com.example.song.paper.message.MessageBean;
import com.example.song.paper.mine.MineBean;
import com.example.song.paper.personal.personal_center.PersonCenterBean;
import com.example.song.paper.common.DynamicBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface HttpService {
    //用户注册
    @POST("app_api/register.php")
    Observable<BaseResponse<String>> register(@Body RequestBody body);
    //获取验证码
    @POST("app_api/getcode.php")
    Observable<BaseResponse<String>> getCode(@Body RequestBody body);
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
    //获取关注和粉丝
    @POST("app_api/fans_and_focus.php")
    Observable<BaseResponse<List<FansAndFocusBean>>> getFansAndFocusData(@Body RequestBody body);
    //获取个人中心信息
    @POST("app_api/personal_info.php")
    Observable<BaseResponse<PersonCenterBean>> getPersonalCenterData(@Body RequestBody body);
    //获取个人中心拍卖
    @POST("app_api/personal_auction.php")
    Observable<BaseResponse<List<AuctionBean>>> getPersonalAuctionData(@Body RequestBody body);
    //获取个人中心动态
    @POST("app_api/personal_dynamic.php")
    Observable<BaseResponse<List<DynamicBean>>> getPersonalDynamicData(@Body RequestBody body);
    //关注
    @POST("app_api/focus.php")
    Observable<BaseResponse<Boolean>> focus(@Body RequestBody body);
    //搜索
    @POST("app_api/search.php")
    Observable<BaseResponse<List<AuctionBean>>> search(@Body RequestBody body);
    //发布动态
    @POST("app_api/release_dynamic.php")
    Observable<BaseResponse<Boolean>> ReleaseDynamic(@Body RequestBody body);
    //发布拍卖
    @POST("app_api/release_auction.php")
    Observable<BaseResponse<Boolean>> ReleaseAuction(@Body RequestBody body);
    //上传图片
    @POST("app_api/upload.php")
    @Multipart
    Observable<BaseResponse<List<String>>> uploadImage(@Part("data")RequestBody body, @Part MultipartBody.Part[] part);
    //获取拍卖详情
    @POST("app_api/auction_detail.php")
    Observable<BaseResponse<AuctionBean>> getAuctionDetailData(@Body RequestBody body);
}
