package com.example.song.paper.release.release_dynamic;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.example.song.paper.R;
import com.example.song.paper.base.BaseActivity;
import com.example.song.paper.common.ImageDisplayActivity;
import com.example.song.paper.common.UploadPhotoAdapter;
import com.example.song.paper.utils.ExceptionHandler;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * User: song
 * Date: 2019/4/13
 * Time: 14:41
 */
public class ReleaseDynamicActivity extends BaseActivity<ReleaseDynamicPresenter> implements ReleaseDynamicConstract.IReleaseDynamicView {
    private static final int REQUEST_CODE = 0x00000011;
    private static final int DISPLAY_IMAGE = 200;
    private static final int DELETE_IMAGE = 201;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.text_num)
    TextView textNum;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.release)
    TextView release;
    private ArrayList<String> list;
    private UploadPhotoAdapter adapter;
    private String addPic = "add_pic" + R.drawable.add_pic;
    @Override
    protected int getLayout() {
        return R.layout.release_dynamic;
    }

    @Override
    protected ReleaseDynamicPresenter initPresent() {
        return new ReleaseDynamicPresenter();
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
        title.setText("发布动态");
        recyclerview.setLayoutManager(new GridLayoutManager(this, 3));
        list = new ArrayList<>();
        list.add(addPic);
        adapter = new UploadPhotoAdapter(ReleaseDynamicActivity.this, list);
        adapter.setOnItemClickListener((position, view) -> {
            if (list.get(position).contains("add_pic")) {
                ImageSelector.builder()
                        .useCamera(true)
                        .setSingle(false)
                        .setViewImage(false)
                        .setMaxSelectCount(9 - adapter.getItemCount() + 1)
                        .start(ReleaseDynamicActivity.this, REQUEST_CODE);
            } else {
                Intent intent = new Intent(ReleaseDynamicActivity.this, ImageDisplayActivity.class);
                intent.putExtra("image", list.get(position));
                intent.putExtra("position", position);
                startActivityForResult(intent, DISPLAY_IMAGE);
            }
        });
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void ReleaseAuctionSuccess(Boolean b) {

    }

    @Override
    public void ReleaseAuctionFail(ExceptionHandler.ResponeThrowable e) {

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

    @OnClick(R.id.release)
    public void onViewClicked() {
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
