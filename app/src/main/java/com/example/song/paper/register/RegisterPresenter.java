package com.example.song.paper.register;

import com.example.song.paper.base.BasePresenter;

public class RegisterPresenter extends BasePresenter<RegisterContract.IRegisterView> implements RegisterContract.IRegisterPresenter {
    private RegisterModel model;

    public RegisterPresenter() {
        model = new RegisterModel();
    }

    @Override
    public void register(String username, String password) {

    }

    @Override
    public void getCode(String username) {

    }

    @Override
    public void forget_pwd(String username, String password, String code) {

    }
}
