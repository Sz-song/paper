package com.example.song.paper.release.release_dynamic;

import com.example.song.paper.base.BaseResponse;
import com.example.song.paper.common.DynamicBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * User: song
 * Date: 2019/4/13
 * Time: 14:34
 */
public class ReleaseDynamicModel implements ReleaseDynamicConstract.IReleaseDynamicModel {

    @Override
    public Observable<BaseResponse<Boolean>> ReleaseAuction(DynamicBean bean) {
        return null;
    }
}
