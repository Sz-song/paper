package com.example.song.paper.fansandfocus;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.base.BaseFragment;
import com.example.song.paper.common.LoadingDialog;
import com.example.song.paper.common.OnPositionClickListener;
import com.example.song.paper.global.GlideApp;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.L;
import com.example.song.paper.utils.Sp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * User: song
 * Date: 2019/4/10
 * Time: 21:45
 */
public class FansAndFocusFragment extends BaseFragment<FansAndFocusPresenter> implements FansAndFocusConstract.IFansAndFocusView {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.nodata_img)
    ImageView nodataImg;
    @BindView(R.id.nodata)
    TextView nodata;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private String userid;//被查看人id;
    private int page;
    private int type;//"0关注","1粉丝"
    private List<FansAndFocusBean> list;
    private LoadingDialog dialog;
    private FansAndFocusAdapter adapter;
    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.common_fragment, container, false);
    }

    @Override
    protected FansAndFocusPresenter initPresent() {
        return new FansAndFocusPresenter();
    }

    @Override
    protected void initEvent(View view) {
        page=0;
        type = getArguments().getInt("type");
        userid = getArguments().getString("userid");
        list=new ArrayList<>();
        dialog=new LoadingDialog(getContext());
        swipe.setColorSchemeResources(R.color.gold);
        GlideApp.with(getActivity())
                .load(R.drawable.nodata)
                .override(nodataImg.getWidth(), nodataImg.getHeight())
                .into(nodataImg);
        nodata.setText("暂时没有数据");
        LinearLayoutManager manager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(manager);
        adapter = new FansAndFocusAdapter(getContext(),list);
        adapter.setFocusClickListener(position -> {
              dialog.show();
              presenter.focus(Sp.getString(getContext(),AppConstant.UID),list.get(position).getId(),position);
        });
        recyclerview.setAdapter(adapter);
        swipe.setOnRefreshListener(() -> {
            page = 0;
            list.clear();
            adapter.notifyDataSetChanged();
            dialog.show();
            presenter.initList(userid,type,page);
        });
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int lastPosition = -1;
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager instanceof GridLayoutManager) {
                        lastPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else if (layoutManager instanceof LinearLayoutManager) {
                        lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                        int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                        ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(lastPositions);
                    }
                    if (lastPosition == recyclerView.getLayoutManager().getItemCount() - 1&&lastPosition>7) {
                        presenter.initList(userid,type,page);
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {
        swipe.setRefreshing(true);
        presenter.initList(userid,type,page);
    }

    @Override
    public void initListSuccess(List<FansAndFocusBean> Beans) {
        swipe.setRefreshing(false);
        list.addAll(Beans);
        if(isAlive){
            dialog.dismiss();
            adapter.notifyItemRangeInserted(list.size() - Beans.size(),Beans.size());
            swipe.setRefreshing(false);
            page++;
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
    public void initListFail(ExceptionHandler.ResponeThrowable e) {
        swipe.setRefreshing(false);
        L.e("msg is"+e.message+"status is"+e.status);
        if(isAlive) {
            dialog.dismiss();
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

    @Override
    public void focusSuccess(Boolean b,int position) {
        dialog.dismiss();
        if(b){
            Toast.makeText(getContext(), "关注成功", Toast.LENGTH_SHORT).show();
            list.get(position).setIsfocus(1);
        }else{
            Toast.makeText(getContext(), "取关成功", Toast.LENGTH_SHORT).show();
            list.get(position).setIsfocus(0);
        }
        adapter.notifyItemChanged(position);
    }

    @Override
    public void focusFail(ExceptionHandler.ResponeThrowable e) {
        dialog.dismiss();
        L.e(e.status + "   " + e.message);
        Toast.makeText(getContext(), "操作失败", Toast.LENGTH_SHORT).show();
    }

}
