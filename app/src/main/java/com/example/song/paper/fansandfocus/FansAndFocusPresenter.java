package com.example.song.paper.fansandfocus;

import com.example.song.paper.base.BaseObserver;
import com.example.song.paper.base.BasePresenter;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.HttpServiceInstance;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * User: song
 * Date: 2019/4/10
 * Time: 21:46
 */
public class FansAndFocusPresenter extends BasePresenter<FansAndFocusConstract.IFansAndFocusView> implements FansAndFocusConstract.IFansAndFocusPresenter {
    private FansAndFocusConstract.IFanAndsFocusModel model;

    public FansAndFocusPresenter() {
        model=new FansAndsFocusModel();
    }

    @Override
    public void initList(String useraccountid, int type, int page) {
        model.initList(useraccountid,type,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<List<FansAndFocusBean>>())
                .subscribe(new BaseObserver <List<FansAndFocusBean>>() {
                    @Override
                    public void onNext(List<FansAndFocusBean> fansAndFocusBeans) {
                        if(view!=null){
                            view.initListSuccess(fansAndFocusBeans);
                        }
                    }
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {
                        if(view!=null){
                            view.initListFail(e);
                        }
                    }
                });
    }
}
