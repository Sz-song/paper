package com.example.song.paper.login.presenter;


import com.example.song.paper.common.utils.ExceptionHandler;
import com.example.song.paper.common.utils.HttpServiceInstance;
import com.example.song.paper.common.utils.L;
import com.example.song.paper.common.base.BaseObserver;
import com.example.song.paper.login.model.ILoginModel;
import com.example.song.paper.login.model.LoginModel;
import com.example.song.paper.login.view.ILoginView;
import com.example.song.paper.login.view.LoginActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements ILoginPresenter {
    private ILoginModel model=new LoginModel();
    private ILoginView loginView=new LoginActivity();
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
                        loginView.showToast("登陆成功");
                    }
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {
                        L.e(e.status+"  "+e.message);
                        loginView.showToast("登陆失败"+e.message);
                    }
                });
    }

    @Override
    public void register(String username, String password) {

    }

    @Override
    public void forget_pwd(String username) {

    }

    @Override
    public void getCode(String username) {

    }
}
