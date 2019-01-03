package com.example.song.paper.common.base;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {
    public BaseObserver() {}

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onError(Throwable e) {
        onError(e);
    }

    @Override
    public void onComplete() {}
    public abstract void onError(Exception e);
}
