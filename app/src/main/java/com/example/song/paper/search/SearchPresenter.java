package com.example.song.paper.search;

import com.example.song.paper.base.BaseObserver;
import com.example.song.paper.base.BasePresenter;
import com.example.song.paper.common.AuctionBean;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.HttpServiceInstance;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter extends BasePresenter<SearchConstract.ISearchView> implements SearchConstract.ISearchPresenter {
    private SearchConstract.ISearchModel model;

    public SearchPresenter() {
        model=new SearchModel();
    }

    @Override
    public void getSearchData(String useraccountid, String query,int page) {
        model.getSearchData(useraccountid,query,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<List<AuctionBean>>())
                .subscribe(new BaseObserver<List<AuctionBean>>() {
                    @Override
                    public void onNext(List<AuctionBean> auctionBeans) {
                        if (view!=null){view.getSearchDataSuccess(auctionBeans);}
                    }
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {
                        if (view!=null){view.getSearchDataFail(e);}
                    }
                });

    }
}
