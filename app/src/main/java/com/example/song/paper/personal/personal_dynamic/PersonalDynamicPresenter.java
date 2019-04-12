package com.example.song.paper.personal.personal_dynamic;

import android.content.Context;

import com.example.song.paper.base.BasePresenter;

public class PersonalDynamicPresenter extends BasePresenter<PersonalDynamicConstract.IPersonalDynamicView> implements PersonalDynamicConstract.IPersonalDynamicPresenter {
    private PersonalDynamicConstract.IPersonalDynamicModel model;

    public PersonalDynamicPresenter() {
        model=new PersonalDynamicModel();
    }

    @Override
    public void initList(String useraccountid, int page) {

    }
}
