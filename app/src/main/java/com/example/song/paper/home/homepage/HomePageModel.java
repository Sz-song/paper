package com.example.song.paper.home.homepage;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.global.HttpService;
import com.example.song.paper.utils.HttpServiceInstance;
import com.example.song.paper.utils.L;
import com.example.song.paper.utils.Md5Utils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public class HomePageModel implements HomePageConstract.IHomePageModel {
    private HttpService httpService;
    public HomePageModel (){httpService = HttpServiceInstance.getInstance();}

    @Override
    public Observable<BaseResponse<HomePageBean>> getHomePageData() {
        String timestamp = Md5Utils.getTimeStamp();
        String randomstr = Md5Utils.getRandomString(10);
        String signature = Md5Utils.getSignature(timestamp,randomstr);
        Map map = new HashMap();
        map.put("timestamp",timestamp);
        map.put("randomstr",randomstr);
        map.put("signature",signature);
        map.put("action","get_home_data");
        Map data = new HashMap();
        map.put("data",data);
        Gson gson=new Gson();
        String str=gson.toJson(map);
        L.e("str is "+str);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),str);
        return httpService.getHomePageData(body);
    }
}
