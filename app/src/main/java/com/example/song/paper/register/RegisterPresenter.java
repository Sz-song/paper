package com.example.song.paper.register;

import com.example.song.paper.base.BaseObserver;
import com.example.song.paper.base.BasePresenter;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.HttpServiceInstance;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter extends BasePresenter<RegisterContract.IRegisterView> implements RegisterContract.IRegisterPresenter {
    private RegisterContract.IRegisterModel model;
    public RegisterPresenter() {
        model = new RegisterModel();
    }

    @Override
    public void register(String username, String password,String code,String name) {
        model.register(username,password,code,name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<String[]>())
                .subscribe(new BaseObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean b) {view.registerSuccess();}
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {view.registerFail(e); }
                });
    }

    @Override
    public void getCode(String phone) {
        model.getCode(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<String[]>())
                .subscribe(new BaseObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean strings) {view.getCodeSuccess();}
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {view.getCodeFail(e); }
                });
    }

}
