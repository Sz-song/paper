package com.example.song.paper.square;

import android.content.Context;

import com.example.song.paper.AppConstant;
import com.example.song.paper.base.BaseObserver;
import com.example.song.paper.base.BasePresenter;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.HttpServiceInstance;
import com.example.song.paper.utils.Sp;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SquarePresenter extends BasePresenter<SquareConstract.ISquareView> implements SquareConstract.ISquarePresenter {
    private SquareConstract.ISquareModel model;

    public SquarePresenter() {
        model=new SquareModel();
    }

    @Override
    public void initList(Context context,int page) {
        model.getSquareData(Sp.getString(context,AppConstant.Base_Url),page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<List<DynamicBean>>())
                .subscribe(new BaseObserver<List<DynamicBean>>() {
                    @Override
                    public void onNext(List<DynamicBean> dynamicBeans) {
                        if(view!=null){ view.initListSuccess(dynamicBeans); }
                    }
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {
                        if(view!=null){ view.initListFail(e); }
                    }
                });
    }
}
