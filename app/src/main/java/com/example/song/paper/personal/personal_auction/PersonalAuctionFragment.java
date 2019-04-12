package com.example.song.paper.personal.personal_auction;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
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
import com.example.song.paper.common.AuctionAdapter;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.L;
import com.example.song.paper.utils.Sp;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;


public class PersonalAuctionFragment extends BaseFragment<PersonalAuctionPresenter> implements PersonalAuctionConstract.IPersonalAuctionView {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.nodata_img)
    ImageView nodataImg;
    @BindView(R.id.nodata)
    TextView nodata;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private int page;
    private AuctionAdapter adapter;
    private List<AuctionBean> list;
    private String userid;

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.common_fragment, container, false);
    }

    @Override
    protected PersonalAuctionPresenter initPresent() {
        return new PersonalAuctionPresenter();
    }

    @Override
    protected void initEvent(View view) {
        page = 0;
        list = new ArrayList<>();
        userid = getArguments().getString("userid");
        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        recyclerview.setLayoutManager(manager);
        adapter = new AuctionAdapter(getContext(), list);
        recyclerview.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter.initList(Sp.getString(getContext(),AppConstant.UID),userid,page);
    }

    @Override
    public void initListSuccess(List<AuctionBean> auctionBeans) {
        list.addAll(auctionBeans);
        if (isAlive) {
            adapter.notifyItemRangeInserted(list.size() - auctionBeans.size(), auctionBeans.size());
            swipe.setRefreshing(false);
            if (auctionBeans.size() > 0) {
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
