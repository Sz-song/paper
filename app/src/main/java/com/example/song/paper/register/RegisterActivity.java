package com.example.song.paper.register;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.song.paper.R;
import com.example.song.paper.base.BaseActivity;
import com.example.song.paper.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.IRegisterView {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.validcode)
    EditText validcode;
    @BindView(R.id.getcode)
    TextView getcode;
    @BindView(R.id.petname)
    EditText petname;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.re_password)
    EditText rePassword;
    @BindView(R.id.register)
    TextView register;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected RegisterPresenter initPresent() {
        return new RegisterPresenter();
    }

    @Override
    protected void initEvent() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.back_black);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if (type.equals("0")) {
            title.setText("注册");
        } else if (type.equals("1")) {
            title.setText("忘记密码");
        }
    }

    @Override
    public void jumpActivity() {

    }


    @Override
    protected void onEventDestroy() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }

    @OnClick(R.id.getcode)
    public void onGetcodeClicked() {
        Toast.makeText(this, "敬请期待", Toast.LENGTH_SHORT).show();
        Intent  intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.register)
    public void onRegisterClicked() {
        Toast.makeText(this, "注册", Toast.LENGTH_SHORT).show();
    }
}
