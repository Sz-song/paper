package com.example.song.paper.search;


import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.base.BaseActivity;
import com.example.song.paper.common.AuctionAdapter;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.L;
import com.example.song.paper.utils.Sp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchConstract.ISearchView {
    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private AuctionAdapter adapter;
    private List<AuctionBean> list;
    private int page;
    @Override
    protected int getLayout() {
        return R.layout.acticity_serach;
    }

    @Override
    protected SearchPresenter initPresent() {
        return new SearchPresenter();
    }

    @Override
    protected void initEvent() {
        //Todo
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_black);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        page = 0;
        list = new ArrayList<>();
        GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerview.setLayoutManager(manager);
        adapter = new AuctionAdapter(this, list);
        recyclerview.setAdapter(adapter);
        search.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                if(search.getText().toString().trim().length()>0){
                    presenter.getSearchData(Sp.getString(SearchActivity.this,AppConstant.UID),search.getText().toString(),page);
                }else{
                    Toast.makeText(SearchActivity.this, "请输入搜索关键字", Toast.LENGTH_SHORT).show();
                }
            }
            return false;
        });
//        swipe.setColorSchemeResources(R.color.gold);
//        GlideApp.with(getActivity())
//                .load(R.drawable.nodata)
//                .override(nodataImg.getWidth(), nodataImg.getHeight())
//                .into(nodataImg);
//        nodata.setText("暂时没有数据");
    }

    @Override
    public void getSearchDataSuccess(List<AuctionBean> beans) {
        list.addAll(beans);
        adapter.notifyItemRangeInserted(list.size() - beans.size(), beans.size());
//        swipe.setRefreshing(false);
        if (beans.size() > 0) {
            page++;
        }
//        if (list.size() == 0) {
//            nodataImg.setVisibility(View.VISIBLE);
//            nodata.setVisibility(View.VISIBLE);
//        } else {
//            nodataImg.setVisibility(View.GONE);
//            nodata.setVisibility(View.GONE);
//        }

    }

    @Override
    public void getSearchDataFail(ExceptionHandler.ResponeThrowable e) {
        L.e(e.status+"  "+e.message);
//        swipe.setRefreshing(false);
//        if (list.size() == 0) {
//            nodataImg.setVisibility(View.VISIBLE);
//            nodata.setVisibility(View.VISIBLE);
//        } else {
//            nodataImg.setVisibility(View.GONE);
//            nodata.setVisibility(View.GONE);
//        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.serach, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.search:
                if(search.getText().toString().trim().length()>0){
                    presenter.getSearchData(Sp.getString(SearchActivity.this,AppConstant.UID),search.getText().toString(),page);
                }else{
                    Toast.makeText(SearchActivity.this, "请输入搜索关键字", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }
}
