package com.example.song.paper.personal.personal_dynamic;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.song.paper.R;
import com.example.song.paper.base.BaseFragment;
import com.example.song.paper.base.BasePresenter;
import com.example.song.paper.square.DynamicBean;
import com.example.song.paper.utils.ExceptionHandler;

import java.util.List;

public class PersonalDynamicFragment extends BaseFragment implements PersonalDynamicConstract.IPersonalDynamicView {
    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.common_fragment, container, false);
    }

    @Override
    protected BasePresenter initPresent() {
        return new PersonalDynamicPresenter();
    }

    @Override
    protected void initEvent(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initListSuccess(List<DynamicBean> dynamicBeans) {

    }

    @Override
    public void initListFail(ExceptionHandler.ResponeThrowable e) {

    }
}
