package com.example.song.paper.personal.personal_center;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.song.paper.personal.personal_auction.PersonalAuctionFragment;
import com.example.song.paper.personal.personal_dynamic.PersonalDynamicFragment;

public class PerosnalFragmentAdpter extends FragmentPagerAdapter {
    private String [] mTitles = new String[]{"动态","拍卖"};

    public PerosnalFragmentAdpter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0){
            PersonalAuctionFragment fragment0=new PersonalAuctionFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("type",1);
            fragment0.setArguments(bundle);
            return fragment0;
        }else if (position==1){
            PersonalDynamicFragment fragment0=new PersonalDynamicFragment();
            Bundle bundle=new Bundle();
            bundle.putInt("type",2);
            fragment0.setArguments(bundle);
            return fragment0;
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
    public CharSequence getPageTitle(int position){
        return mTitles[position];
    }
}
