package com.example.song.paper.personal.personal_center;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.song.paper.R;
import com.example.song.paper.base.BaseActivity;
import com.example.song.paper.utils.ExceptionHandler;

public class PersonalCenterActivity extends BaseActivity<PersonalCenterPresenter> implements PersonalCenterConstract.IPersonalCenterView {

    @Override
    protected int getLayout() {
        return R.layout.activity_personal_center;
    }

    @Override
    protected PersonalCenterPresenter initPresent() {
        return new PersonalCenterPresenter();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void getPersonalCenterDataSuccess(PersonCenterBean bean) {

    }

    @Override
    public void getPersonalCenterDataFail(ExceptionHandler.ResponeThrowable e) {

    }
}
