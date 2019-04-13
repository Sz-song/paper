package com.example.song.paper.release.release_auction;

import com.example.song.paper.R;
import com.example.song.paper.base.BaseActivity;
import com.example.song.paper.utils.ExceptionHandler;

/**
 * User: song
 * Date: 2019/4/13
 * Time: 14:25
 */
public class ReleaseAuctionActivity extends BaseActivity<ReleaseAuctionPresenter> implements ReleaseAuctionConstract.IReleaseDynamicView {
    @Override
    protected int getLayout() {
        return R.layout.release_auction;
    }

    @Override
    protected ReleaseAuctionPresenter initPresent() {
        return new ReleaseAuctionPresenter();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void ReleaseDynamicSuccess(Boolean b) {

    }

    @Override
    public void ReleaseDynamicFail(ExceptionHandler.ResponeThrowable e) {

    }
}
