package com.example.song.paper.login.model;

import com.example.song.paper.common.HttpService;
import com.example.song.paper.utils.HttpServiceInstance;
import com.example.song.paper.utils.L;
import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.utils.Md5Utils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;

public class LoginModel implements ILoginModel {
    private HttpService httpService;
    public LoginModel(){httpService = HttpServiceInstance.getInstance();}

    @Override
    public void register(String username, String password) {

    }
    @Override
    public Observable<BaseResponse<String>> login(String username, String password) {
        String timestamp = Md5Utils.getTimeStamp();
        String randomstr = Md5Utils.getRandomString(10);
        String signature = Md5Utils.getSignature(timestamp,randomstr);
        Map map = new HashMap();
        map.put("timestamp",timestamp);
        map.put("randomstr",randomstr);
        map.put("signature",signature);
        map.put("action","login");
        Map data = new HashMap();
        data.put("username",username);
        data.put("password",password);
        map.put("data",data);
        Gson gson=new Gson();
        String str=gson.toJson(map);
        L.e("str is "+str);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),str);
        return httpService.login(body);
    }
}
