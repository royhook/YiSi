package com.yisi.picture.picturemodel.database.table;

import android.content.ContentValues;
import android.database.Cursor;

import com.yisi.picture.picturemodel.bean.Image;

import java.util.ArrayList;
import java.util.List;

import static com.yisi.picture.baselib.database.BaseDatabase.DB_EXCEPTION;
import static com.yisi.picture.baselib.database.BaseDatabase.DB_EXIST;
import static com.yisi.picture.baselib.database.BaseDatabase.DB_FAILED;
import static com.yisi.picture.baselib.database.BaseDatabase.DB_SUCCESS;

/**
 * Created by chenql on 2017/10/24.
 */

public class PictureTable extends BaseTable {

    public static String TABLE_NAME_OWNAD = "image_db";
    private static PictureTable mInstance = null;

    private PictureTable() {

    }


    public ContentValues fillOwnAdValues(Image image) {
        ContentValues values = new ContentValues();
        values.put(ImageColumn.COLUMN_ID, image.getUrl());
        values.put(ImageColumn.COLUMN_IMAGE_URL, image.getUrl());
        values.put(ImageColumn.COLUMN_IMAGE_TYPE, image.getType_id());
        return values;
    }


    public int insertImage(Image image) {
        try {
            Cursor cursor = queryPlantById(image.getUrl());
            int count = cursor.getCount();
            if (count != 0) {
                return DB_EXIST;
            }
            ContentValues contentValues = fillOwnAdValues(image);
            long rowId = mSqLiteDatabase.insert(TABLE_NAME_OWNAD, null, contentValues);
            if (rowId == -1) {
                return DB_FAILED;
            }
            return DB_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DB_EXCEPTION;
    }


    private Cursor queryPlantById(String url) {
        Cursor cursor = mSqLiteDatabase.query(TABLE_NAME_OWNAD, new String[]{ImageColumn.COLUMN_IMAGE_URL}, "img_url=?", new String[]{url}, null, null, null);
        return cursor;
    }

    public static PictureTable getInstance() {
        if (mInstance == null) {
            synchronized (PictureTable.class) {
                if (mInstance == null) {
                    mInstance = new PictureTable();
                }
            }
        }
        return mInstance;
    }


    public List<Image> queryAllImage() {
        Cursor cursor = null;
        try {
            cursor = mSqLiteDatabase.rawQuery("select * from " + TABLE_NAME_OWNAD, null);
            return cursorToMap(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    private List<Image> cursorToMap(Cursor cursor) {
        List<Image> images = new ArrayList<>();
        while (cursor.moveToNext()) {
            Image image = new Image();
            image.setUrl(cursor.getString(ImageColumn.COLUMN_IMAGE_URL_INDEX));
            image.setType_id(cursor.getInt(ImageColumn.COLUMN_IMAGE_TYPE_INDEX));
            images.add(image);
        }
        return images;
    }

    @Override
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_OWNAD + "("
                + ImageColumn.COLUMN_ID + " TEXT PRIMARY KEY ,"
                + ImageColumn.COLUMN_IMAGE_URL + " TEXT ,"
                + ImageColumn.COLUMN_IMAGE_TYPE + " TEXT"
                + ");";
        mSqLiteDatabase.execSQL(sql);
    }

    public interface ImageColumn {

        String COLUMN_ID = "_id";
        /**
         * 图片url
         */
        String COLUMN_IMAGE_URL = "img_url";

        /**
         * 图片的类型
         */
        String COLUMN_IMAGE_TYPE = "img_type";

        int COLUMN_ID_INDEX = 0;
        int COLUMN_IMAGE_URL_INDEX = 1;
        int COLUMN_IMAGE_TYPE_INDEX = 2;
    }
}
