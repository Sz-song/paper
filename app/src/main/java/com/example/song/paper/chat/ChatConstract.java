package com.example.song.paper.chat;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.utils.ExceptionHandler;

import java.util.List;

import io.reactivex.Observable;

public interface ChatConstract {
    interface IChatModel{
        Observable<BaseResponse<List<ChatBean>>> getChatData(String useraccountid, String userid);
        Observable<BaseResponse<Boolean>>sendChat(String useraccountid, String userid,String txt);
    }

    interface IChatView{
        void getChatDataSuccess(List<ChatBean> dynamicBeans);
        void getChatDataFail(ExceptionHandler.ResponeThrowable e);

        void sendChatSuccess(Boolean b);
        void sendChatFail(ExceptionHandler.ResponeThrowable e);
    }

    interface IChatPresenter{
        void getChatData(String useraccountid, String userid);
        void sendChat(String useraccountid, String userid,String txt);
    }
}
