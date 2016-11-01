package vn.edu.android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import vn.edu.android.model.ThongTin;

/**
 * Created by Nguyen Trung Truc on 10/13/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "ThongTin.db";
    private static int DATABASE_VERSION = 1;

    public static String TABLE_NAME = "ThongTin";
    public static String COLUMN_ID = "id";
    public static String COLUMN_NAME = "username";
    public static String COLUMN_PASSWORD = "password";
    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void insertThongTin(ThongTin thongTin)
    {
        db =this.getWritableDatabase();
        ContentValues values=new ContentValues();
        String sql="select * from ThongTin";
        Cursor cursor=db.rawQuery(sql,null);
        int count=cursor.getCount();
        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME,thongTin.getUsername());
        values.put(COLUMN_PASSWORD,thongTin.getPassword());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public String searchMatKhau(String username)
    {
        db=this.getReadableDatabase();
        String sql="select username, password from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(sql,null);
        String a,b;
        b="Not found";
        if(cursor.moveToFirst())
        {
            do {
                a=cursor.getString(0);

                if(a.equals(username))
                {
                    b=cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return  b;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = " CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT NOT NULL, " + COLUMN_PASSWORD + " TEXT NOT NULL " + " )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME);
        onCreate(db);
    }

}
