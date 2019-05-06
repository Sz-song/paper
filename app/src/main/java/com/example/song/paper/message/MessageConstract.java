package com.example.song.paper.message;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.utils.ExceptionHandler;

import java.util.List;

import io.reactivex.Observable;

public interface MessageConstract {
    interface IMessageModel{
        Observable<BaseResponse<List<MessageBean>>> getMessageData(String useraccountid,int page);
    }

    interface IMessageView{
        void getMessageDataSuccess(List<MessageBean> messageBeans);
        void getMessageDataFail(ExceptionHandler.ResponeThrowable e);
    }

    interface IMessagePresenter{
        void getMessageData(String useraccountid,int page);
    }
}
