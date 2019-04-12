package com.example.song.paper.personal.personal_dynamic;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.base.BaseFragment;
import com.example.song.paper.common.DynamicAdapter;
import com.example.song.paper.common.DynamicBean;
import com.example.song.paper.global.GlideApp;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.L;
import com.example.song.paper.utils.Sp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PersonalDynamicFragment extends BaseFragment<PersonalDynamicPresenter> implements PersonalDynamicConstract.IPersonalDynamicView {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.nodata_img)
    ImageView nodataImg;
    @BindView(R.id.nodata)
    TextView nodata;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    private int page;
    private DynamicAdapter adapter;
    private List<DynamicBean> list;
    private String userid;
    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.common_fragment, container, false);
    }

    @Override
    protected PersonalDynamicPresenter initPresent() {
        return new PersonalDynamicPresenter();
    }

    @Override
    protected void initEvent(View view) {
        page = 0;
        list = new ArrayList<>();
        userid = getArguments().getString("userid");
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(manager);
        adapter = new DynamicAdapter(getContext(), list);
        recyclerview.setAdapter(adapter);
        swipe.setColorSchemeResources(R.color.gold);
        GlideApp.with(getActivity())
                .load(R.drawable.nodata)
                .override(nodataImg.getWidth(), nodataImg.getHeight())
                .into(nodataImg);
        nodata.setText("暂时没有数据");
    }

    @Override
    protected void initData() {
        presenter.initList(Sp.getString(getContext(),AppConstant.UID),userid,page);
    }

    @Override
    public void initListSuccess(List<DynamicBean> dynamicBeans) {
        list.addAll(dynamicBeans);
        if (isAlive) {
            adapter.notifyItemRangeInserted(list.size() - dynamicBeans.size(), dynamicBeans.size());
            swipe.setRefreshing(false);
            if (dynamicBeans.size() > 0) {
                page++;
            }
            if (list.size() == 0) {
                nodataImg.setVisibility(View.VISIBLE);
                nodata.setVisibility(View.VISIBLE);
            } else {
                nodataImg.setVisibility(View.GONE);
                nodata.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void initListFail(ExceptionHandler.ResponeThrowable e) {
        L.e(e.status+"  "+e.message);
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
}
