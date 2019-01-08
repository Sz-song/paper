package com.example.song.paper.login.presenter;

public interface ILoginPresenter {
    void login(String username,String password);
    void register(String username,String password);
    void forget_pwd(String username);
    void getCode(String username);
}
