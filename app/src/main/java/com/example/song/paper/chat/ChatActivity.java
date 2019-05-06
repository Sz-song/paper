package com.example.song.paper.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.base.BaseActivity;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.Sp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity<ChatPresenter> implements ChatConstract.IChatView {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.input)
    EditText input;
    @BindView(R.id.send)
    TextView send;
    private String userid;
    private String name;
    private ChatAdapter adapter;
    private List<ChatBean> list;
    @Override
    protected int getLayout() {
        return R.layout.activity_chat;
    }

    @Override
    protected ChatPresenter initPresent() {
        return new ChatPresenter();
    }

    @Override
    protected void initEvent() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_black);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        Intent intent=getIntent();
        userid=intent.getStringExtra("userid");
        name=intent.getStringExtra("name");
        title.setText(name);
        list=new ArrayList<>();
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        adapter = new ChatAdapter(this,list,name);
        recyclerview.setAdapter(adapter);
        presenter.getChatData(Sp.getString(this,AppConstant.UID),userid);
    }


    @Override
    public void getChatDataSuccess(List<ChatBean> dynamicBeans) {

    }

    @Override
    public void getChatDataFail(ExceptionHandler.ResponeThrowable e) {

    }

    @Override
    public void sendChatSuccess(Boolean b) {

    }

    @Override
    public void sendChatFail(ExceptionHandler.ResponeThrowable e) {

    }

    @OnClick(R.id.send)
    public void onViewClicked() {
    }
}
