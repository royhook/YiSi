package com.yisi.picture.picturemodel.adapter;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yisi.picture.baselib.utils.GlideUtils;
import com.yisi.picture.baselib.utils.ViewUtils;
import com.yisi.picture.picturemodel.R;
import com.yisi.picture.picturemodel.bean.AliAlbum;

import java.util.List;

/**
 * Created by chenql on 2017/5/29.
 */

public class MainPageChildAliAdapter extends BaseQuickAdapter<AliAlbum, BaseViewHolder> {

    private OnAliClickListener mClickListener;

    public void setOnAliClickListener(OnAliClickListener listener) {
        this.mClickListener = listener;
    }

    public MainPageChildAliAdapter(@Nullable List<AliAlbum> data) {
        super(R.layout.adapter_alipic, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, final AliAlbum aliAlbum, int position) {
        TextView titleView = helper.getView(R.id.aa_title);
        titleView.setText(aliAlbum.getName());

        View firstView = helper.getView(R.id.vd_first);
        View secondtView = helper.getView(R.id.vd_second);
        View thridView = helper.getView(R.id.vd_third);
        View fourthView = helper.getView(R.id.vd_fourth);

        TextView firstTvTitle = ViewUtils.findView(firstView, R.id.tv_detiles_title);
        ImageView firstIvImage = ViewUtils.findView(firstView, R.id.iv_detiles_img);

        TextView secondTvTitle = ViewUtils.findView(secondtView, R.id.tv_detiles_title);
        ImageView secondIvImage = ViewUtils.findView(secondtView, R.id.iv_detiles_img);

        TextView thridTvTitle = ViewUtils.findView(thridView, R.id.tv_detiles_title);
        ImageView thridIvImage = ViewUtils.findView(thridView, R.id.iv_detiles_img);

        TextView fourceTvTitle = ViewUtils.findView(fourthView, R.id.tv_detiles_title);
        ImageView fourceIvImage = ViewUtils.findView(fourthView, R.id.iv_detiles_img);

        final TextView moreView = helper.getView(R.id.aa_more);

        moreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(moreView, "暂无更多分类", Snackbar.LENGTH_SHORT).show();
            }
        });


        firstTvTitle.setText(aliAlbum.getList().get(0).getName());
        secondTvTitle.setText(aliAlbum.getList().get(1).getName());
//        thridTvTitle.setText(aliAlbum.getList().get(2).getName());
//        fourceTvTitle.setText(aliAlbum.getList().get(3).getName());

        firstView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null)
                    mClickListener.onClick(aliAlbum.getList().get(0).getId(), aliAlbum.getList().get(0).getName());
            }
        });

        secondtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null)
                    mClickListener.onClick(aliAlbum.getList().get(1).getId(), aliAlbum.getList().get(1).getName());

            }
        });

        thridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null)
                    mClickListener.onClick(aliAlbum.getList().get(2).getId(), aliAlbum.getList().get(2).getName());

            }
        });

        fourthView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null)
                    mClickListener.onClick(aliAlbum.getList().get(3).getId(), aliAlbum.getList().get(3).getName());
            }
        });

        switch (position) {
            case 0:
                displayImage("http://img01.sogoucdn.com/app/a/100520020/36d2bcb2849a3048af887f6454a9f114", firstIvImage);
                displayImage("http://img04.sogoucdn.com/app/a/100520020/fb3c50836ff3e776b2709db2440c90d0", secondIvImage);
                displayImage("http://img02.sogoucdn.com/app/a/100520020/af7557bf9e695fc95a0624621ad0096d", thridIvImage);
                displayImage("http://image.tianjimedia.com/uploadImages/2016/054/03/TQ4I30K93C28.jpg", fourceIvImage);

                break;

            case 1:
                displayImage("http://image.tianjimedia.com/uploadImages/2017/152/29/11XG04Z6EIX2.jpg", firstIvImage);
                displayImage("http://image.tianjimedia.com/uploadImages/2017/150/50/70G96W1Q5A8X.jpg", secondIvImage);
                displayImage("http://image.tianjimedia.com/uploadImages/2017/152/35/U63U7NYLNJD2.jpg", thridIvImage);
                displayImage("http://image.tianjimedia.com/uploadImages/2017/152/24/V2FP21333J2B.jpg", fourceIvImage);

                break;

            case 2:
                displayImage("http://image.tianjimedia.com/uploadImages/2015/236/30/93F1KZRQWFJY.jpg", firstIvImage);
                displayImage("http://image.tianjimedia.com/uploadImages/2015/081/53/8JDD4LQ234E7.jpg", secondIvImage);
                displayImage("http://image.tianjimedia.com/uploadImages/2016/043/43/TIY77FO000M4.jpg", thridIvImage);
                displayImage("http://image.tianjimedia.com/uploadImages/2016/046/24/F7Q259GSYPZF.jpg", fourceIvImage);

                break;

            case 3:
                displayImage("http://img03.sogoucdn.com/app/a/100520020/044996f0d2ec73e1ee4b90c4f4ffda80", firstIvImage);
                displayImage("http://image.tianjimedia.com/uploadImages/2017/151/27/V8MX13S2BE4P.jpg", secondIvImage);
                displayImage("http://image.tianjimedia.com/uploadImages/2017/126/51/V9367VMX19A4.jpg", thridIvImage);
                displayImage("http://img04.sogoucdn.com/app/a/100520020/29b0689a18ec1a6cac5508ad6def4dff", fourceIvImage);

                break;

            case 4:
                displayImage("http://image.tianjimedia.com/uploadImages/2016/344/04/08JJSWT1B9A3.jpg", firstIvImage);
                displayImage("http://image.tianjimedia.com/uploadImages/2015/270/29/D8J91T8M0773.jpg", secondIvImage);
                displayImage("http://image.tianjimedia.com/uploadImages/2016/017/08/07M34983SWG9.jpg", thridIvImage);
                displayImage("http://image.tianjimedia.com/uploadImages/2017/152/09/T4M213HYO62D.jpg", fourceIvImage);

                break;

            case 5:
                displayImage("http://image.tianjimedia.com/uploadImages/2017/152/09/T4M213HYO62D.jpg", firstIvImage);
                displayImage("http://image.tianjimedia.com/uploadImages/2015/299/48/3B3IS25Q0Z33.jpg", secondIvImage);
                displayImage("http://img04.sogoucdn.com/app/a/100520020/1c9c79cd760fd79078935d0e193fa923", thridIvImage);
                displayImage("http://img01.sogoucdn.com/app/a/100520020/f486bb4193ac82613c785205dc5f663c", fourceIvImage);

                break;

            case 6:
                displayImage("http://image.tianjimedia.com/uploadImages/2016/048/42/S0D3QH22T0D6.jpg", firstIvImage);
                displayImage("http://img04.sogoucdn.com/app/a/100520020/fb3c50836ff3e776b2709db2440c90d0", secondIvImage);
                displayImage("http://img02.sogoucdn.com/app/a/100520020/af7557bf9e695fc95a0624621ad0096d", thridIvImage);
                displayImage("http://image.tianjimedia.com/uploadImages/2016/054/03/TQ4I30K93C28.jpg", fourceIvImage);

                break;

            case 7:
                displayImage("http://img01.sogoucdn.com/app/a/100520020/36d2bcb2849a3048af887f6454a9f114", firstIvImage);
                displayImage("http://img04.sogoucdn.com/app/a/100520020/fb3c50836ff3e776b2709db2440c90d0", secondIvImage);
                displayImage("http://img02.sogoucdn.com/app/a/100520020/af7557bf9e695fc95a0624621ad0096d", thridIvImage);
                displayImage("http://image.tianjimedia.com/uploadImages/2016/054/03/TQ4I30K93C28.jpg", fourceIvImage);

                break;

        }

    }

    public interface OnAliClickListener {
        void onClick(int id, String name);
    }

    private void displayImage(String url, ImageView imageView) {
        GlideUtils.displayCircleImage(url, imageView);
    }


}
