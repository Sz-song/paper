package com.example.song.paper.message;

import com.example.song.paper.base.BaseObserver;
import com.example.song.paper.base.BasePresenter;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.HttpServiceInstance;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MessagePresenter extends BasePresenter<MessageConstract.IMessageView> implements MessageConstract.IMessagePresenter {
    private MessageConstract.IMessageModel model;

    public MessagePresenter() {
        model =new MessageModel();
    }

    @Override
    public void getMessageData(String useraccountid,int page){
        model.getMessageData(useraccountid,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<List<MessageBean>>())
                .subscribe(new BaseObserver<List<MessageBean>>() {
                    @Override
                    public void onNext(List<MessageBean> messageBeans) {
                        if(view!=null) {
                            view.getMessageDataSuccess(messageBeans);
                        }
                    }
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {
                        if(view!=null) {
                            view.getMessageDataFail(e);
                        }
                    }
                });
    }
}
