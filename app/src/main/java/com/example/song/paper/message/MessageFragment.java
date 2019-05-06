package com.example.song.paper.message;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.base.BaseFragment;
import com.example.song.paper.global.GlideApp;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.Sp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageFragment extends BaseFragment<MessagePresenter> implements MessageConstract.IMessageView {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.nodata_img)
    ImageView nodataImg;
    @BindView(R.id.nodata)
    TextView nodata;
    private int page;
    private MessageAdapter adapter;
    private List<MessageBean> list;
    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.common_layout, container, false);
    }

    @Override
    protected MessagePresenter initPresent() {
        return new MessagePresenter();
    }

    @Override
    protected void initEvent(View view) {
        title.setText("消息");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        swipe.setOnRefreshListener(() -> {
            page = 0;
            list.clear();
            adapter.notifyDataSetChanged();
            presenter.getMessageData(Sp.getString(getContext(),AppConstant.UID),page);
        });
        GlideApp.with(getActivity())
                .load(R.drawable.nodata)
                .override(nodataImg.getWidth(), nodataImg.getHeight())
                .into(nodataImg);
        nodata.setText("暂时没有数据");
        page=0;
        list=new ArrayList<>();
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(manager);
        adapter=new MessageAdapter(getContext(),list);
        recyclerview.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter.getMessageData(Sp.getString(getContext(),AppConstant.UID),page);
    }
    @Override
    public void getMessageDataSuccess(List<MessageBean> messageBeans) {
            list.addAll(messageBeans);
            if(isAlive){
                adapter.notifyItemRangeInserted(list.size() - messageBeans.size(),messageBeans.size());
                swipe.setRefreshing(false);
                if(messageBeans.size()>0){
                    page++;
                }
                if (list.size() == 0) {
                    nodataImg.setVisibility(View.VISIBLE);
                    nodata.setVisibility(View.VISIBLE);
                }else {
                    nodataImg.setVisibility(View.GONE);
                    nodata.setVisibility(View.GONE);
                }
            }
        }

    @Override
    public void getMessageDataFail(ExceptionHandler.ResponeThrowable e) {
        if(isAlive) {
            swipe.setRefreshing(false);
            if (list.size() == 0) {
                nodataImg.setVisibility(View.VISIBLE);
                nodata.setVisibility(View.VISIBLE);
            } else {
                nodataImg.setVisibility(View.GONE);
                nodata.setVisibility(View.GONE);
            }
        }
    }

    @OnClick({R.id.nodata_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.nodata_img:
                break;
        }
    }

}
