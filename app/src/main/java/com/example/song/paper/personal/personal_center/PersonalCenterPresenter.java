package com.example.song.paper.personal.personal_center;

import com.example.song.paper.base.BaseObserver;
import com.example.song.paper.base.BasePresenter;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.HttpServiceInstance;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * User: song
 * Date: 2019/4/11
 * Time: 20:28
 */
public class PersonalCenterPresenter extends BasePresenter<PersonalCenterConstract.IPersonalCenterView> implements PersonalCenterConstract.IPersonalCenterPresenter {
    private PersonalCenterConstract.IPersonalCenterModel model;

    PersonalCenterPresenter() {
        model=new PersonalCenterModel();
    }

    @Override
    public void getPersonalCenterData(String useraccountid, String userid) {
        model.getPersonalCenterData(useraccountid,userid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new HttpServiceInstance.ErrorTransformer<PersonCenterBean>())
                .subscribe(new BaseObserver<PersonCenterBean>() {
                    @Override
                    public void onNext(PersonCenterBean bean) {
                        if(view!=null){ view.getPersonalCenterDataSuccess(bean); }
                    }
                    @Override
                    public void onError(ExceptionHandler.ResponeThrowable e) {
                        if(view!=null){ view.getPersonalCenterDataFail(e); }
                    }
                });
    }
}
