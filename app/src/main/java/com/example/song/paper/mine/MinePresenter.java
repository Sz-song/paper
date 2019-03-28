package com.example.song.paper.mine;

import com.example.song.paper.base.BaseObserver;
import com.example.song.paper.base.BasePresenter;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.HttpServiceInstance;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MinePresenter extends BasePresenter<MineConstract.IMineView> implements MineConstract.IMinePresenter {
    private MineConstract.IMineModel model;
    public MinePresenter() {
        model=new MineModel();
    }

    @Override
        public void getMineData(String uid) {
            model.getMineData(uid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(new HttpServiceInstance.ErrorTransformer<MineBean>())
                    .subscribe(new BaseObserver<MineBean>() {
                        @Override
                        public void onNext(MineBean mineBean) { if(view!=null){view.getMineDataSuccess(mineBean);} }
                        @Override
                        public void onError(ExceptionHandler.ResponeThrowable e) {if(view!=null){view.getMineDataFail(e);}}
                    });
        }
    }
