package com.yisi.picture.picturemodel.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.yisi.picture.baselib.application.YiSiApplication;
import com.yisi.picture.baselib.database.BaseDatabase;
import com.yisi.picture.picturemodel.database.table.BaseTable;
import com.yisi.picture.picturemodel.database.table.PictureTable;
import com.yisi.picture.picturemodel.database.table.PlantTable;

/**
 * Created by chenql on 2017/10/22.
 */

public class YisiDatabase extends BaseDatabase {

    public final static String DATABASE_NAME = "yisipic";

    public final static int VERSION = 1;

    private static YisiDatabase mInstance = null;

    private YisiDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        mSqLiteDatabase = getDataBase();
    }

    public void init() {
        BaseTable.setDatabase(mSqLiteDatabase);
        PlantTable.getInstance().createTable();
        PictureTable.getInstance().createTable();
    }

    public static YisiDatabase getInstance() {
        if (mInstance == null) {
            synchronized (YisiDatabase.class) {
                if (mInstance == null) {
                    mInstance = new YisiDatabase(YiSiApplication.mGlobleContext);
                }
            }
        }
        return mInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);

    }


}
