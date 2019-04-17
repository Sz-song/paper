package com.example.song.paper.release.release_auction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.example.song.paper.AppConstant;
import com.example.song.paper.R;
import com.example.song.paper.base.BaseActivity;
import com.example.song.paper.common.CustomDatePicker;
import com.example.song.paper.common.ImageDisplayActivity;
import com.example.song.paper.common.LoadingDialog;
import com.example.song.paper.common.UploadPhotoAdapter;
import com.example.song.paper.utils.ExceptionHandler;
import com.example.song.paper.utils.L;
import com.example.song.paper.utils.Sp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.Luban;

/**
 * User: song
 * Date: 2019/4/13
 * Time: 14:25
 */
public class ReleaseAuctionActivity extends BaseActivity<ReleaseAuctionPresenter> implements ReleaseAuctionConstract.IReleaseDynamicView {
    private static final int REQUEST_CODE = 0x00000011;
    private static final int DISPLAY_IMAGE = 200;
    private static final int DELETE_IMAGE = 201;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.title_auction)
    EditText titleAuction;
    @BindView(R.id.time_start)
    TextView timeStart;
    @BindView(R.id.time_end)
    TextView timeEnd;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.price_start)
    EditText priceStart;
    private CustomDatePicker picker, picker2;
    private ArrayList<String> list;
    private UploadPhotoAdapter adapter;
    private String addPic = "add_pic" + R.drawable.add_pic;
    private LoadingDialog dialog;

    @Override
    protected int getLayout() {
        return R.layout.release_auction;
    }

    @Override
    protected ReleaseAuctionPresenter initPresent() {
        return new ReleaseAuctionPresenter();
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
        title.setText("发布拍卖");
        recyclerview.setLayoutManager(new GridLayoutManager(this, 3));
        list = new ArrayList<>();
        dialog = new LoadingDialog(this);
        list.add(addPic);
        adapter = new UploadPhotoAdapter(ReleaseAuctionActivity.this, list);
        adapter.setOnItemClickListener((position, view) -> {
            if (list.get(position).contains("add_pic")) {
                ImageSelector.builder()
                        .useCamera(true)
                        .setSingle(false)
                        .setViewImage(false)
                        .setMaxSelectCount(9 - adapter.getItemCount() + 1)
                        .start(ReleaseAuctionActivity.this, REQUEST_CODE);
            } else {
                Intent intent = new Intent(ReleaseAuctionActivity.this, ImageDisplayActivity.class);
                intent.putExtra("image", list.get(position));
                intent.putExtra("position", position);
                startActivityForResult(intent, DISPLAY_IMAGE);
            }
        });
        recyclerview.setAdapter(adapter);
        initDatePicker();
    }

    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        L.e("now is  +" + now);
        timeStart.setText(now);
        timeEnd.setText(now);
        picker = new CustomDatePicker(this, time -> { // 回调接口，获得选中的时间
            timeStart.setText(time);
        }, "1900-01-01 00:00", "2100-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        picker2 = new CustomDatePicker(this, time -> { // 回调接口，获得选中的时间
            timeEnd.setText(time);
        }, "1900-01-01 00:00", "2100-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        picker.showSpecificTime(true); // 显示时和分
        picker.setIsLoop(true); // 允许循环滚动
        picker2.showSpecificTime(true); // 显示时和分
        picker2.setIsLoop(true); // 允许循环滚动
    }

    @Override
    public void ReleaseAuctionSuccess(Boolean b) {
        dialog.dismiss();
        Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void ReleaseAuctionFail(ExceptionHandler.ResponeThrowable e) {
        dialog.dismiss();
        L.e(e.message+ "  "+e.status);
        list.clear();
        list.add(addPic);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "发布失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void UploadImageSuccess(List<String> images) {
        list.clear();
        list.addAll(images);
        presenter.ReleaseAuction(Sp.getString(this,AppConstant.UID),titleAuction.getText().toString(),timeStart.getText().toString(),timeEnd.getText().toString(),priceStart.getText().toString(),list);
    }

    @Override
    public void UploadImageFail(ExceptionHandler.ResponeThrowable e) {
        dialog.dismiss();
        Toast.makeText(this, "上传图片失败", Toast.LENGTH_SHORT).show();
        L.e(e.status+"   "+e.message);
    }

    @OnClick({R.id.time_start, R.id.time_end,R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.time_start:
                picker.show(timeStart.getText().toString());
                break;
            case R.id.time_end:
                picker2.show(timeEnd.getText().toString());
                break;
            case R.id.submit:
                if(titleAuction.getText().toString().trim().length()>0){
                    if(checkTime(timeStart.getText().toString(),timeEnd.getText().toString())){
                        if(priceStart.getText().toString().trim().length()>0) {
                            if (list.size() == 1) {
                                Toast.makeText(this, "最少上传一张图片", Toast.LENGTH_SHORT).show();
                            } else if (list.size() > 1 && list.size() < 9) {
                                list.remove(list.size() - 1);
                                dialog.show();
                                compressImage(list);
                            } else {
                                dialog.show();
                                compressImage(list);
                            }
                        }else{
                            Toast.makeText(this, "请输入起拍价格", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this, "时间输入有误", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            ArrayList<String> images = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            if ((images.size() + adapter.getItemCount()) == 10) {
                list.remove(adapter.getItemCount() - 1);
                for (int i = images.size() - 1; i >= 0; i--) {
                    list.add(0, images.get(i));
                }
                adapter.notifyDataSetChanged();
            } else {
                for (int i = images.size() - 1; i >= 0; i--) {
                    list.add(0, images.get(i));
                }
                adapter.notifyDataSetChanged();
            }
        }
        if (requestCode == DISPLAY_IMAGE && resultCode == DELETE_IMAGE) {
            int position = data.getIntExtra("position", 999);
            if (position <= list.size()) {
                if (list.get(list.size() - 1).contains("add_pic")) {
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                } else {
                    list.remove(position);
                    list.add(addPic);
                    adapter.notifyDataSetChanged();
                }

            }
        }
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

    @SuppressLint("CheckResult")
    public void compressImage(final List<String> image) {
        Flowable.just(image)
                .observeOn(Schedulers.io())
                .map(list -> Luban.with(ReleaseAuctionActivity.this).load(list).get())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(files -> presenter.UploadImage(files));
    }

    private boolean checkTime(String timeStart, String timeEnd) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(timeStart));
            c2.setTime(df.parse(timeEnd));
            int result = c1.compareTo(c2);
            if (result == 0) {
                return false;
            } else if (result < 0) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            L.e(e.getMessage());
            return false;
        }
    }

}
