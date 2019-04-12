package com.example.song.paper.personal.personal_dynamic;

import android.content.Context;

import com.example.song.paper.base.BaseObserver;
import com.example.song.paper.base.BasePresenter;
import com.example.song.paper.common.DynamicBean;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.HttpServiceInstance;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PersonalDynamicPresenter extends BasePresenter<PersonalDynamicConstract.IPersonalDynamicView> implements PersonalDynamicConstract.IPersonalDynamicPresenter {
    private PersonalDynamicConstract.IPersonalDynamicModel model;

    public PersonalDynamicPresenter() {
        model=new PersonalDynamicModel();
    }

    @Override
    public void initList(String useraccountid,String userid, int page) {
        model.initList(useraccountid,userid,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<List<DynamicBean>>())
                .subscribe(new BaseObserver<List<DynamicBean>>() {
                    @Override
                    public void onNext(List<DynamicBean> dynamicBeans) {
                        if(view!=null){
                            view.initListSuccess(dynamicBeans);
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
