package com.example.song.paper.mine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.base.BaseFragment;
import com.example.song.paper.fansandfocus.FansAndFocusActivity;
import com.example.song.paper.global.GlideApp;
import com.example.song.paper.personal.personal_center.PersonalCenterActivity;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.L;
import com.example.song.paper.utils.Sp;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MineFragment extends BaseFragment<MinePresenter> implements MineConstract.IMineView {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.portrait)
    CircleImageView portrait;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.introduce)
    TextView introduce;
    @BindView(R.id.my_fans)
    TextView myFans;
    @BindView(R.id.my_focus)
    TextView myFocus;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    protected MinePresenter initPresent() {
        return new MinePresenter();
    }

    @Override
    protected void initEvent(View view) {
        title.setText("我");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        swipe.setOnRefreshListener(() -> {
            presenter.getMineData(Sp.getString(getContext(),AppConstant.UID));
        });
    }

    @Override
    protected void initData() {
        swipe.setRefreshing(true);
        presenter.getMineData(Sp.getString(getContext(),AppConstant.UID));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getMineDataSuccess(MineBean bean) {
        swipe.setRefreshing(false);
        GlideApp.with(getContext())
                .load(AppConstant.Base_Url+bean.getPortrait())
                .override(100,100)
                .placeholder(R.drawable.imageholder)
                .into(portrait);
        name.setText(bean.getName());
        introduce.setText(bean.getIntroduce());
        myFans.setText("我的粉丝:"+bean.getFans_num());
        myFocus.setText("我的关注:"+bean.getFocus_num());
    }
    @Override
    public void getMineDataFail(ExceptionHandler.ResponeThrowable e) {
        L.e(e.status+"  "+e.message);
    }

    @OnClick({R.id.portrait, R.id.my_fans, R.id.my_focus, R.id.my_auction, R.id.my_home, R.id.my_dynamic})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.portrait:
                intent=new Intent(getContext(),PersonalCenterActivity.class);
                intent.putExtra("userid",Sp.getString(getContext(),AppConstant.UID));
                startActivity(intent);
                break;
            case R.id.my_fans:
                intent=new Intent(getContext(),FansAndFocusActivity.class);
                intent.putExtra("position",1);
                intent.putExtra("userid",Sp.getString(getContext(),AppConstant.UID));
                startActivity(intent);
                break;
            case R.id.my_focus:
                intent=new Intent(getContext(),FansAndFocusActivity.class);
                intent.putExtra("position",0);
                intent.putExtra("userid",Sp.getString(getContext(),AppConstant.UID));
                startActivity(intent);
                break;
            case R.id.my_auction:
                intent=new Intent(getContext(),PersonalCenterActivity.class);
                intent.putExtra("position",1);
                intent.putExtra("userid",Sp.getString(getContext(),AppConstant.UID));
                startActivity(intent);
                break;
            case R.id.my_home:
                intent=new Intent(getContext(),PersonalCenterActivity.class);
                intent.putExtra("position",0);
                intent.putExtra("userid",Sp.getString(getContext(),AppConstant.UID));
                startActivity(intent);
                break;
            case R.id.my_dynamic:
                intent=new Intent(getContext(),PersonalCenterActivity.class);
                intent.putExtra("position",0);
                intent.putExtra("userid",Sp.getString(getContext(),AppConstant.UID));
                startActivity(intent);
                break;
        }
    }
}
