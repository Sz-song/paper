package com.example.song.paper.register;

import com.example.song.paper.base.BasePresenter;

public class RegisterPresenter extends BasePresenter<RegisterContract.IRegisterView> implements RegisterContract.IRegisterPresenter {
    private RegisterContract.IRegisterModel model;
    public RegisterPresenter() {
        model = new RegisterModel();
    }

    @Override
    public void register(String username, String password) {
        model.register(username,password);
    }

    @Override
    public void getCode(String username) {

    }

}
