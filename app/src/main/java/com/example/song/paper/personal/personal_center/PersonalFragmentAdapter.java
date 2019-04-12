package com.example.song.paper.personal.personal_center;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.song.paper.personal.personal_auction.PersonalAuctionFragment;
import com.example.song.paper.personal.personal_dynamic.PersonalDynamicFragment;

public class PersonalFragmentAdapter extends FragmentPagerAdapter {
    private String [] mTitles = new String[]{"动态","拍卖"};
    private String userid;

    public PersonalFragmentAdapter(FragmentManager fm, String userid) {
        super(fm);
        this.userid = userid;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0){
            PersonalDynamicFragment fragment0=new PersonalDynamicFragment();
            Bundle bundle=new Bundle();
            bundle.putString("userid",userid);
            fragment0.setArguments(bundle);
            return fragment0;
        }else if (position==1){
            PersonalAuctionFragment fragment1=new PersonalAuctionFragment();
            Bundle bundle=new Bundle();
            bundle.putString("userid",userid);
            fragment1.setArguments(bundle);
            return fragment1;
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
