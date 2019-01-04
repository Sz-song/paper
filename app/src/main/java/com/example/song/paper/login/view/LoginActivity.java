package com.example.song.paper.login.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.song.paper.R;
import com.example.song.paper.common.ExceptionHandler;
import com.example.song.paper.common.base.BaseObserver;
import com.example.song.paper.common.base.BaseResponse;
import com.example.song.paper.login.model.LoginModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
    private LoginModel model=new LoginModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.login, R.id.forget_password, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                model.login(username.getText().toString(),password.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new BaseObserver<BaseResponse<String[]>>() {

                            @Override
                            public void onNext(BaseResponse<String[]> baseResponse) {

                            }

                            @Override
                            public void onError(ExceptionHandler.ResponeThrowable e) {

                            }
                        });
                break;
            case R.id.forget_password:
                break;
            case R.id.register:
                break;
        }
    }
}
