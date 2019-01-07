package com.example.song.paper.login.presenter;


import com.example.song.paper.common.utils.ExceptionHandler;
import com.example.song.paper.common.utils.HttpServiceInstance;
import com.example.song.paper.common.utils.L;
import com.example.song.paper.common.base.BaseObserver;
import com.example.song.paper.login.model.ILoginModel;
import com.example.song.paper.login.model.LoginModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements ILoginPresenter {
    private ILoginModel model=new LoginModel();
    @Override
    public void login(String username, String password) {
        model.login(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<String>())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onNext(String s) {
                        L.e("s is:"+s);
                    }

                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {
                        L.e(e.status+"  "+e.message);
                    }
                });
    }

    @Override
    public void register(String username, String password) {

    }
}
