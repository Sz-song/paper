package com.example.song.paper.personal.personal_dynamic;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.global.HttpService;
import com.example.song.paper.common.DynamicBean;
import com.example.song.paper.utils.HttpServiceInstance;

import java.util.List;

import io.reactivex.Observable;

public class PersonalDynamicModel implements PersonalDynamicConstract.IPersonalDynamicModel {
    private HttpService httpService;
    public PersonalDynamicModel() {httpService=HttpServiceInstance.getInstance();}

    @Override
    public Observable<BaseResponse<List<DynamicBean>>> initList(String useraccountid, int page) {
        return null;
    }
}
