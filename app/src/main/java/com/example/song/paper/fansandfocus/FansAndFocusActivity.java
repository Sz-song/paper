package com.example.song.paper.fansandfocus;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.example.song.paper.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FansAndFocusActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tablelayout)
    XTabLayout tablelayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_tablayout);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_black);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light);
        title.setText("好友");
        int position = getIntent().getIntExtra("position", 0);
        String userid=getIntent().getStringExtra("userid");
        FansAndFocusFragmentAdapter adapter = new FansAndFocusFragmentAdapter(getSupportFragmentManager(),userid);
        viewPager.setAdapter(adapter);
        tablelayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(position);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;

    }
}
