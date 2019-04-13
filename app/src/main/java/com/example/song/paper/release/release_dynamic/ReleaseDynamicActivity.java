package com.example.song.paper.release.release_dynamic;

import com.example.song.paper.base.BaseActivity;
import com.example.song.paper.utils.ExceptionHandler;

/**
 * User: song
 * Date: 2019/4/13
 * Time: 14:41
 */
public class ReleaseDynamicActivity extends BaseActivity<ReleaseDynamicPresenter> implements ReleaseDynamicConstract.IReleaseDynamicView {
    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected ReleaseDynamicPresenter initPresent() {
        return null;
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void initListSuccess(Boolean b) {

    }

    @Override
    public void initListFail(ExceptionHandler.ResponeThrowable e) {

    }
}
