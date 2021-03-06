package com.example.song.paper.square;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import com.example.song.paper.R;
import com.example.song.paper.base.BaseFragment;
import com.example.song.paper.common.DynamicAdapter;
import com.example.song.paper.common.DynamicBean;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.L;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

public class SquareFragment extends BaseFragment<SquarePresenter> implements SquareConstract.ISquareView {
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
    private DynamicAdapter adapter;
    private List<DynamicBean> list;
    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.common_layout, container, false);
    }

    @Override
    protected SquarePresenter initPresent() {
        return new SquarePresenter();
    }

    @Override
    protected void initEvent(View view) {
        title.setText("广场");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        page=0;
        list=new ArrayList<>();
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(manager);
        adapter=new DynamicAdapter(getContext(),list);
        recyclerview.setAdapter(adapter);
    }
    @Override
    protected void initData() {
        presenter.initList(getContext(),page);
    }

    @OnClick({R.id.nodata_img, R.id.nodata})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.nodata_img:
                page = 0;
                list.clear();
                swipe.setRefreshing(true);
                adapter.notifyDataSetChanged();
                presenter.initList(getContext(),page);
                break;
            case R.id.nodata:
                page = 0;
                list.clear();
                swipe.setRefreshing(true);
                adapter.notifyDataSetChanged();
                presenter.initList(getContext(),page);
                break;
        }
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
