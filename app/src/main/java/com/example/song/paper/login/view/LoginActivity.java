package com.example.song.paper.login.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.song.paper.R;
import com.example.song.paper.common.utils.GlideApp;
import com.example.song.paper.login.presenter.ILoginPresenter;
import com.example.song.paper.login.presenter.LoginPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity implements ILoginView {

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
    @BindView(R.id.background)
    ImageView background;
    private ILoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ButterKnife.bind(this);
        GlideApp.with(this).load(R.drawable.bg_login).into(background);
        loginPresenter=new LoginPresenter();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.login, R.id.forget_password, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                loginPresenter.login(username.getText().toString(),password.getText().toString());
                break;
            case R.id.forget_password:
                break;
            case R.id.register:
                loginPresenter.register(username.getText().toString(),password.getText().toString());
                break;
        }
    }
}
