package com.example.song.paper.common.base;

import com.example.song.paper.common.utils.ExceptionHandler;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {
    public BaseObserver() {

    }
    @Override
    public void onError(Throwable e) {
        // L.e("throwable is "+e.getMessage());
        // onError(ExceptionHandler.handleException(e));

        if (e instanceof ExceptionHandler.ServerException){
            onError(ExceptionHandler.handleException(e));
        }
        else if(e instanceof ExceptionHandler.ResponeThrowable){
            onError((ExceptionHandler.ResponeThrowable)e);

        }
        else {
            onError(ExceptionHandler.handleException(e));
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        //  Toast.makeText(context, "建立连接", Toast.LENGTH_SHORT).show();

        //可以弹出Dialog 提示正在加载
        //  showDialog();

    }

    @Override
    public void onComplete() {

        //可以取消Dialog 加载完毕
        //    hideDialog();
    }


    public abstract void onError(ExceptionHandler.ResponeThrowable e);

}
