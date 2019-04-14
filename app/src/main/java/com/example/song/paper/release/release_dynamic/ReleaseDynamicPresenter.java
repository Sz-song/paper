package com.example.song.paper.release.release_dynamic;

import android.content.Context;

import com.example.song.paper.base.BaseObserver;
import com.example.song.paper.base.BasePresenter;
import com.example.song.paper.common.DynamicBean;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.HttpServiceInstance;

import java.io.File;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * User: song
 * Date: 2019/4/13
 * Time: 14:33
 */
public class ReleaseDynamicPresenter extends BasePresenter<ReleaseDynamicConstract.IReleaseDynamicView> implements ReleaseDynamicConstract.IReleaseDynamicPresenter {
    private ReleaseDynamicConstract.IReleaseDynamicModel model;

    public ReleaseDynamicPresenter() {
        model=new ReleaseDynamicModel();
    }

    @Override
    public void ReleaseDynamic(String useraccountid,String content,List<String> list) {
        model.ReleaseDynamic(useraccountid,content,list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<Boolean>())
                .subscribe(new BaseObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if(view!=null){
                            view.ReleaseDynamicSuccess(aBoolean);
                        }
                    }
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {
                        if(view!=null){
                            view.ReleaseDynamicFail(e);
                        }
                    }
                });
    }

    @Override
    public void UploadImage(List<File> images) {
        model.UploadImage(images)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<List<String>>())
                .subscribe(new BaseObserver<List<String>>() {
                    @Override
                    public void onNext(List<String> strings) {
                        if(view!=null){
                            view.UploadImageSuccess(strings);
                        }
                    }
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {
                        if(view!=null){
                            view.UploadImageFail(e);
                        }
                    }
                });
    }
}
