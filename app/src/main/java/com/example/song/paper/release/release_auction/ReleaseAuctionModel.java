package com.example.song.paper.release.release_auction;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.global.HttpService;
import com.example.song.paper.utils.HttpServiceInstance;
import com.example.song.paper.utils.L;
import com.example.song.paper.utils.Md5Utils;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * User: song
 * Date: 2019/4/13
 * Time: 14:31
 */
public class ReleaseAuctionModel implements ReleaseAuctionConstract.IReleaseDynamicModel {
    private HttpService httpService;
    public ReleaseAuctionModel() {httpService = HttpServiceInstance.getInstance();}
    @Override
    public Observable<BaseResponse<Boolean>> ReleaseAuction(String useraccountid,String name,String time_start,String time_end,String price,List<String> list) {
        String timestamp = Md5Utils.getTimeStamp();
        String randomstr = Md5Utils.getRandomString(10);
        String signature = Md5Utils.getSignature(timestamp,randomstr);
        Map map = new HashMap();
        map.put("timestamp",timestamp);
        map.put("randomstr",randomstr);
        map.put("signature",signature);
        map.put("action","release_auction");
        Map data = new HashMap();
        data.put("name",name);
        data.put("useraccountid",useraccountid);
        data.put("time_start",time_start);
        data.put("time_end",time_end);
        data.put("price",price);
        data.put("list",list);
        map.put("data",data);
        Gson gson=new Gson();
        String str=gson.toJson(map);
        L.e("str is "+str);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),str);
        return httpService.ReleaseAuction(body);
    }

    @Override
    public Observable<BaseResponse<List<String>>> UploadImage(List<File> images) {
        String timestamp = Md5Utils.getTimeStamp();
        String randomstr = Md5Utils.getRandomString(10);
        String signature = Md5Utils.getSignature(timestamp,randomstr);
        Map map = new HashMap();
        map.put("timestamp", timestamp);
        map.put("randomstr", randomstr);
        map.put("signature", signature);
        map.put("action", "upload");
        Map data = new HashMap();
        map.put("data", data);
        Gson gson = new Gson();
        String str = gson.toJson(map);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), str);
        MultipartBody.Part[] part = new MultipartBody.Part[images.size()];
        for (int i = 0; i < images.size(); i++) {
            RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), images.get(i));
            part[i] = MultipartBody.Part.createFormData("files[]", images.get(i).getName(), photoBody);
        }
        L.e("str is "+str);
        return httpService.uploadImage(body,part);
    }
}
