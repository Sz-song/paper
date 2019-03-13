package com.example.song.paper.message;

import com.example.song.paper.base.BaseResponse;

import java.util.List;

import io.reactivex.Observable;

public interface MessageConstract {
    interface IMessageModel{
        Observable<BaseResponse<List<MessageBean>>> getMessageData(String useraccountid,int page);
    }

    interface IMessageView{
        void updata(List<MessageBean> messageBeans, boolean success);
    }

    interface IMessagePresenter{
        void getMessageData(String useraccountid,int page);
    }
}
