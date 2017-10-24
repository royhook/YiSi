package com.yisi.picture.picturemodel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.database.BaseDatabase;
import com.yisi.picture.picturemodel.bean.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenql on 2017/10/24.
 */

public class ImageDatabase extends BaseDatabase {

    public final static String DATABASE_NAME = "yisipic";
    public static String TABLE_NAME_OWNAD = "image_db";

    public final static int VERSION = 1;
    private static ImageDatabase mInstance = null;

    private ImageDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        mSqLiteDatabase = getDataBase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
        db.execSQL("CREATE TABLE " + TABLE_NAME_OWNAD + "("
                + ImageColumn.COLUMN_ID + " TEXT PRIMARY KEY ,"
                + ImageColumn.COLUMN_IMAGE_URL + " TEXT ,"
                + ImageColumn.COLUMN_IMAGE_TYPE + " TEXT"
                + ");");
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

    public static ImageDatabase getInstance() {
        if (mInstance == null) {
            synchronized (ImageDatabase.class) {
                if (mInstance == null) {
                    mInstance = new ImageDatabase(YiSiApplication.mGlobleContext);
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
            image.setType_id(cursor.getString(ImageColumn.COLUMN_IMAGE_TYPE_INDEX));
            images.add(image);
        }
        return images;
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
