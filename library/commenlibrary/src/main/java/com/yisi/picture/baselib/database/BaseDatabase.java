package com.yisi.picture.baselib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class BaseDatabase extends SQLiteOpenHelper {

    /**
     * 数据库操作相关异常
     */
    public final static int DB_SUCCESS = 0;
    public final static int DB_FAILED = -1;
    public final static int DB_EXCEPTION = -2;
    public final static int DB_EXIST = -3;

    protected SQLiteDatabase mSqLiteDatabase;

    public BaseDatabase(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * Add a column to a table using ALTER TABLE.
     *
     * @param dbTable          name of the table
     * @param columnName       name of the column to add
     * @param columnDefinition SQL for the column definition
     */
    protected void addColumn(SQLiteDatabase db, String dbTable, String columnName, String columnDefinition) {
        db.execSQL("ALTER TABLE " + dbTable + " ADD COLUMN " + columnName + " " + columnDefinition);
    }

    /**
     * 获取可写数据库
     *
     * @return
     */
    public SQLiteDatabase getDataBase() {
        try {
            return getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                return getReadableDatabase();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
