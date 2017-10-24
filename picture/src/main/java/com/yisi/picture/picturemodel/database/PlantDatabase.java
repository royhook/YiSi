package com.yisi.picture.picturemodel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.database.BaseDatabase;
import com.yisi.picture.picturemodel.bean.Image;
import com.yisi.picture.picturemodel.bean.PlantImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenql on 2017/10/22.
 */

public class PlantDatabase extends BaseDatabase {

    public final static String DATABASE_NAME = "yisipic";
    public static String TABLE_NAME_OWNAD = "plant_db";

    public final static int VERSION = 1;
    private static PlantDatabase mInstance = null;

    private PlantDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        mSqLiteDatabase = getDataBase();
    }

    public static PlantDatabase getInstance() {
        if (mInstance == null) {
            synchronized (PlantDatabase.class) {
                if (mInstance == null) {
                    mInstance = new PlantDatabase(YiSiApplication.mGlobleContext);
                }
            }
        }
        return mInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
        db.execSQL("CREATE TABLE " + TABLE_NAME_OWNAD + "("
                + PlantColumn.COLUMN_ID + " TEXT PRIMARY KEY ,"
                + PlantColumn.COLUMN_PLANT_TITLE + " TEXT, "
                + PlantColumn.COLUMN_PLANT_IMG_URL + " TEXT, "
                + PlantColumn.COLUMN_JSON_TEXT + " TEXT"
                + ");");
    }

    public ContentValues fillOwnAdValues(PlantImage plantImage) {
        ContentValues values = new ContentValues();
        values.put(PlantColumn.COLUMN_ID, plantImage.getId());
        values.put(PlantColumn.COLUMN_PLANT_TITLE, plantImage.getName());
        values.put(PlantColumn.COLUMN_PLANT_IMG_URL, plantImage.getImage_url());
        values.put(PlantColumn.COLUMN_JSON_TEXT, new Gson().toJson(plantImage.getImage_list()));
        return values;
    }

    private List<PlantImage> cursorToMap(Cursor cursor) {
        List<PlantImage> images = new ArrayList<>();
        while (cursor.moveToNext()) {
            PlantImage plantImage = new PlantImage();
            plantImage.setId(cursor.getString(PlantColumn.COLUMN_ID_INDEX));
            plantImage.setName(cursor.getString(PlantColumn.COLUMN_PLANT_TITLE_INDEX));
            plantImage.setImage_url(cursor.getString(PlantColumn.COLUMN_PLANT_IMG_URL_INDEX));
            String list = cursor.getString(PlantColumn.COLUMN_JSON_TEXT_INDEX);
            List<Image> imageList = new Gson().fromJson(list, new TypeToken<List<Image>>() {
            }.getType());
            plantImage.setImage_list(imageList);
            images.add(plantImage);
        }
        return images;
    }


    /**
     * 插入一条记录
     */
    public int insertPlantImage(PlantImage plantImage) {
        try {
            Cursor cursor = queryPlantById(plantImage.getId());
            int count = cursor.getCount();
            if (count != 0) {
                return DB_EXIST;
            }
            ContentValues contentValues = fillOwnAdValues(plantImage);
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


    private Cursor queryPlantById(String id) {
        Cursor cursor = mSqLiteDatabase.query(TABLE_NAME_OWNAD, new String[]{PlantColumn.COLUMN_ID}, "_id=?", new String[]{id}, null, null, null);
        return cursor;
    }


    /**
     * 查询所有下载信息
     *
     * @return
     */
    public List<PlantImage> queryAllPlant() {
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


//
//    public int updatePlant(long id, ContentValues values) {
//        try {
//            if (mSqLiteDatabase.update(TABLE_NAME_OWNAD, values, PictureColumn.COLUMN_ID + "=" + "'" + id + "'", null) <= 0) {
//                return DB_FAILED;
//            }
//            return DB_SUCCESS;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return DB_EXCEPTION;
//    }
//

    public interface PlantColumn {
        /**
         * ownad id
         */
        String COLUMN_ID = "_id";

        /**
         * 套图名称
         */
        String COLUMN_PLANT_TITLE = "title";

        /**
         * 套图的大图url
         */
        String COLUMN_PLANT_IMG_URL = "img_url";


        /**
         * json内容
         */
        String COLUMN_JSON_TEXT = "img_list";

        int COLUMN_ID_INDEX = 0;
        int COLUMN_PLANT_TITLE_INDEX = 1;
        int COLUMN_PLANT_IMG_URL_INDEX = 2;
        int COLUMN_JSON_TEXT_INDEX = 3;
    }
}
