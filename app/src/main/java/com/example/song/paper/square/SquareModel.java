package com.example.song.paper.square;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.common.DynamicBean;
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

public class SquareModel implements SquareConstract.ISquareModel {
    private HttpService httpService;
    public SquareModel(){httpService = HttpServiceInstance.getInstance();}

    @Override
    public Observable<BaseResponse<List<DynamicBean>>> getSquareData(String useraccountid, int page) {
        String timestamp = Md5Utils.getTimeStamp();
        String randomstr = Md5Utils.getRandomString(10);
        String signature = Md5Utils.getSignature(timestamp,randomstr);
        Map map = new HashMap();
        map.put("timestamp",timestamp);
        map.put("randomstr",randomstr);
        map.put("signature",signature);
        map.put("action","get_square_data");
        Map data = new HashMap();
        data.put("useraccountid",useraccountid);
        data.put("page",page);
        data.put("page_size",10);
        map.put("data",data);
        Gson gson=new Gson();
        String str=gson.toJson(map);
        L.e("str is "+str);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),str);
        return httpService.getSquareData(body);
    }
}
