package com.example.song.paper.chat;

import com.example.song.paper.base.BasePresenter;

public class ChatPresenter extends BasePresenter implements ChatConstract.IChatPresenter {
    private ChatConstract.IChatModel model;

    public ChatPresenter() {
        model=new ChatModel();
    }

    @Override
    public void getChatData(String useraccountid, String userid) {

    }

    @Override
    public void sendChat(String useraccountid, String userid, String txt) {

    }
}
