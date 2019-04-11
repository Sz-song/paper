package com.example.song.paper.fansandfocus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * User: song
 * Date: 2019/4/10
 * Time: 21:43
 */
public class FansAndFocusFragmentAdapter extends FragmentPagerAdapter {
    private String [] mTitles = new String[]{"关注","粉丝"};
    private String userid;
    public FansAndFocusFragmentAdapter(FragmentManager fm,String userid){
        super(fm);
        this.userid=userid;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0){
            FansAndFocusFragment fragment1 =new FansAndFocusFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("type",0);
            bundle.putString("userid",userid);
            fragment1.setArguments(bundle);
            return fragment1;
        }
        else if (position==1){
            FansAndFocusFragment fragment2 =new FansAndFocusFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("type",1);
            bundle.putString("userid",userid);
            fragment2.setArguments(bundle);
            return fragment2;
        }
        return new FansAndFocusFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
    public CharSequence getPageTitle(int position){
        return mTitles[position];
    }
}