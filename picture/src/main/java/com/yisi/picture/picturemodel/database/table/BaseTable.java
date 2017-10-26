package com.yisi.picture.picturemodel.database.table;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by chenql on 2017/10/24.
 */

public abstract class BaseTable {

    protected static SQLiteDatabase mSqLiteDatabase;

    public static void setDatabase(SQLiteDatabase sqlLteDatabase) {
        mSqLiteDatabase = sqlLteDatabase;
    }

    public abstract void createTable();
}
