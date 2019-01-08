package com.example.song.paper.login;
import com.example.song.paper.base.BasePresenter;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.HttpServiceInstance;
import com.example.song.paper.utils.L;
import com.example.song.paper.base.BaseObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> implements LoginContract.ILoginPresenter {
    private LoginContract.ILoginModel model;

    public LoginPresenter() {
        model=new LoginModel();
    }
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
                        if(view!=null) {
                            view.showToast("登陆成功");
                        }
                    }
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {
                        L.e(e.status+"  "+e.message);
                        if(view!=null) {
                            view.showToast("登陆失败" + e.message);
                        }
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
