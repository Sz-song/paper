package com.example.song.paper.chat;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.global.HttpService;
import com.example.song.paper.utils.HttpServiceInstance;

import java.util.List;

import io.reactivex.Observable;

public class ChatModel implements ChatConstract.IChatModel {

    private HttpService httpService;

    public ChatModel() { httpService = HttpServiceInstance.getInstance(); }

    @Override
    public Observable<BaseResponse<List<ChatBean>>> getChatData(String useraccountid, String userid) {
        return null;
    }

    @Override
    public Observable<BaseResponse<Boolean>> sendChat(String useraccountid, String userid, String txt) {
        return null;
    }
}
