package com.example.song.paper.fansandfocus;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.global.HttpService;
import com.example.song.paper.utils.HttpServiceInstance;
import com.example.song.paper.utils.L;
import com.example.song.paper.utils.Md5Utils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * User: song
 * Date: 2019/4/10
 * Time: 21:13
 */
public class FansAndsFocusModel implements FansAndFocusConstract.IFanAndsFocusModel {
    private HttpService httpService;
    public FansAndsFocusModel(){httpService = HttpServiceInstance.getInstance();}
    @Override
    public Observable<BaseResponse<List<FansAndFocusBean>>> initList(String useraccountid, int type, int page) {
        String timestamp = Md5Utils.getTimeStamp();
        String randomstr = Md5Utils.getRandomString(10);
        String signature = Md5Utils.getSignature(timestamp,randomstr);
        Map map = new HashMap();
        map.put("timestamp",timestamp);
        map.put("randomstr",randomstr);
        map.put("signature",signature);
        map.put("action","fans_and_focus");
        Map data = new HashMap();
        data.put("useraccountid",useraccountid);
        data.put("type",type);
        data.put("page",page);
        data.put("page_size",10);
        map.put("data",data);
        Gson gson=new Gson();
        String str=gson.toJson(map);
        L.e("str is "+str);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),str);
        return httpService.getFansAndFocusData(body);
    }

    @Override
    public Observable<BaseResponse<Boolean>> focus(String useraccountid, String userid) {
        String timestamp = Md5Utils.getTimeStamp();
        String randomstr = Md5Utils.getRandomString(10);
        String signature = Md5Utils.getSignature(timestamp,randomstr);
        Map map = new HashMap();
        map.put("timestamp",timestamp);
        map.put("randomstr",randomstr);
        map.put("signature",signature);
        map.put("action","focus");
        Map data = new HashMap();
        data.put("useraccountid",useraccountid);
        data.put("userid",userid);
        map.put("data",data);
        Gson gson=new Gson();
        String str=gson.toJson(map);
        L.e("str is "+str);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),str);
        return httpService.focus(body);
    }
}
