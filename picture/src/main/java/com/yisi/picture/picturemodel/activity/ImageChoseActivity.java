package com.yisi.picture.picturemodel.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.base.BaseActivity;
import com.yisi.picture.baselib.database.BaseDatabase;
import com.yisi.picture.baselib.utils.ViewUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.adapter.ImageChoseAdapter;
import com.yisi.picture.picturemodel.bean.Image;
import com.yisi.picture.picturemodel.bean.PlantImage;
import com.yisi.picture.picturemodel.database.table.PlantTable;

import java.util.List;

/**
 * Created by chenql on 2017/10/12.
 */

public class ImageChoseActivity extends BaseActivity {
    public static final String KEY_PLANT = "key_plant";
    public static final String KEY_PLANT_ID = "key_plant_id";
    public static final String KEY_PLANT_TITLE = "key_plant_title";
    public static final String KEY_PLANT_IMG = "key_plant_image";
    public static final String KEY_IS_COLLECT = "key_is_collect";
    private RecyclerView mRecyclerView;
    private ImageView mImageView;
    TextView mTitleView;
    private ImageView mCollectView;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_chose);
        mRecyclerView = findView(R.id.rv_chose);
        mImageView = findView(R.id.iv_chose);
        mCollectView = findView(R.id.iv_collect);
        mTitleView = findView(R.id.tv_plant_title);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

        final String json = getIntent().getStringExtra(KEY_PLANT);
        final String id = getIntent().getStringExtra(KEY_PLANT_ID);
        final String title = getIntent().getStringExtra(KEY_PLANT_TITLE);
        final String imgUrl = getIntent().getStringExtra(KEY_PLANT_IMG);
        if (PlantTable.getInstance().isExist(id)) {
            mCollectView.setImageResource(R.mipmap.collect_chose);
        }

        if (getIntent().getBooleanExtra(KEY_IS_COLLECT, false)) {
            mCollectView.setVisibility(View.GONE);
        }


        Gson gson = new Gson();
        mTitleView.setText(title);
        final List<Image> images = gson.fromJson(json, new TypeToken<List<Image>>() {
        }.getType());
        ImageChoseAdapter adapter = new ImageChoseAdapter(images);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityOptions transitionActivityOptions = null;
                ImageView imageView = ViewUtils.findView(view, R.id.adapter_plant_img);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(ImageChoseActivity.this, imageView, getString(R.string.trs_plant_operate));
                    startActivity(ImageOperateActivity.getDataIntent(position, json), transitionActivityOptions.toBundle());
                } else {
                    ImageChoseActivity.this.startActivity(ImageOperateActivity.getDataIntent(position, json));
                }
            }
        });

        mCollectView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlantImage plantImage = new PlantImage();
                plantImage.setId(id);
                plantImage.setName(title);
                plantImage.setImage_url(imgUrl);
                plantImage.setImage_list(images);
                int resCode = PlantTable.getInstance().insertPlantImage(plantImage);
                if (resCode == BaseDatabase.DB_SUCCESS) {
                    mCollectView.setImageResource(R.mipmap.collect_chose);
                    Snackbar.make(v, R.string.collect_success, Snackbar.LENGTH_SHORT).show();
                } else if (resCode == BaseDatabase.DB_EXIST) {
                    Snackbar.make(v, R.string.collect_failed_exist, Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }


    public static Intent getDateIntent(String list, String id, String title, String imgurl, boolean collect) {
        Intent intent = new Intent();
        intent.setClass(YiSiApplication.mGlobleContext, ImageChoseActivity.class);
        intent.putExtra(KEY_PLANT, list);
        intent.putExtra(KEY_PLANT_ID, id);
        intent.putExtra(KEY_PLANT_TITLE, title);
        intent.putExtra(KEY_PLANT_IMG, imgurl);
        intent.putExtra(KEY_IS_COLLECT, collect);
        return intent;
    }

    public static Intent getDateIntent(String list, String id, String title, String imgurl) {
        return getDateIntent(list, id, title, imgurl, false);
    }


}
