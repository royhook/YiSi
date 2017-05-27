package com.yisi.picture.picturemodel.activity;


import com.yisi.picture.baselib.base.BaseActivity;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.fragment.AlbumDiagramDetilsFragment;

/**
 * Created by chenql on 2017/4/26.
 */

public class AlbumDiagramDetilsActivity extends BaseActivity {
    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_details);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_detiles_content,new AlbumDiagramDetilsFragment(), "add").commit();
    }

    @Override
    protected void initData() {
    }
}
