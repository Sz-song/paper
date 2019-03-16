package com.example.song.paper.login;

import android.content.Intent;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.song.paper.R;
import com.example.song.paper.base.BaseActivity;
import com.example.song.paper.home.HomeActivity;
import com.example.song.paper.register.RegisterActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.ILoginView {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.forget_password)
    TextView forgetPassword;
    @BindView(R.id.register)
    TextView register;
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter initPresent() {
        return  new LoginPresenter();
    }

    @Override
    protected void initEvent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ButterKnife.bind(this);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if(username.getText().toString().length()>3&&password.getText().toString().length()>5){
                    login.setActivated(true);
                }else{
                    login.setBackground(getResources().getDrawable(R.drawable.btn_activity_un));
                }
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if(username.getText().toString().length()>3&&password.getText().toString().length()>5){
                    login.setBackground(getResources().getDrawable(R.drawable.btn_activity));
                }else{
                    login.setBackground(getResources().getDrawable(R.drawable.btn_activity_un));
                }
            }
        });
    }
    @Override
    public void jumpActivity() {
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.login, R.id.forget_password, R.id.register})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.login:
                presenter.login(username.getText().toString(),password.getText().toString());
                break;
            case R.id.forget_password:
                intent=new Intent(this,HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.register:
                intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

}
