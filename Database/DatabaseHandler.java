package vn.edu.android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import vn.edu.android.model.CongViec;

/**
 * Created by Nguyen Trung Truc on 10/13/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "CongViec.db";
    private static int DATABASE_VERSION = 1;

    public static String TABLE_NAME = "CongViec";
    public static String COLUMN_ID = "id";
    public static String COLUMN_TITLE = "title";
    public static String COLUMN_DESCRIPTION = "description";
    public static String COLUMN_DATE = "dateFinish";
    public static String COLUMN_HOUR = "hourFinish";
    Context context;
    SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public List<CongViec> layDanhSachCongViec() {
        List<CongViec> list = new ArrayList<CongViec>();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CongViec cv = new CongViec();
            cv.setTenCongViec(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
            cv.setMoTa(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
            cv.setNgayHT(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
            cv.setGioHT(cursor.getString(cursor.getColumnIndex(COLUMN_HOUR)));
            list.add(cv);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public void themCongViec(CongViec congViec) {
        db = this.getWritableDatabase();
        ContentValues row = new ContentValues();
        row.put(COLUMN_TITLE, congViec.getTenCongViec());
        row.put(COLUMN_DESCRIPTION, congViec.getMoTa());
        row.put(COLUMN_DATE, congViec.getNgayHT());
        row.put(COLUMN_HOUR, congViec.getGioHT());
        db.insert(TABLE_NAME, null, row);
        db.close();
        layDanhSachCongViec();
    }

    public int cannhatCongViec(CongViec congViec){
        int ret = -1;
        ContentValues row = new ContentValues();
        row.put(COLUMN_TITLE,congViec.getTenCongViec());
        row.put(COLUMN_DESCRIPTION,congViec.getMoTa());
        row.put(COLUMN_DATE,congViec.getNgayHT());
        row.put(COLUMN_HOUR,congViec.getGioHT());
        SQLiteDatabase db = this.getWritableDatabase();
       // ret = db.update(TABLE_NAME,row,COLUMN_ID + "=?",new String[]{congViec.getId()});
        ret = db.update(TABLE_NAME,row,COLUMN_ID + "=?",new String[]{String.valueOf(congViec.getId())});
        db.close();
        return ret;
    }

   /*public CongViec getCongViec(String id){
        CongViec congViec;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + COLUMN_ID + " = " + "'"+ id + "'";
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst()){
            String tencv = c.getString(c.getColumnIndex(COLUMN_TITLE));
            String mota = c.getString(c.getColumnIndex(COLUMN_DESCRIPTION));
            String ngayht = c.getString(c.getColumnIndex(COLUMN_DATE));
            String gioht = c.getString(c.getColumnIndex(COLUMN_HOUR));
            congViec = new CongViec(id,tencv,mota,ngayht,gioht);
        }else
            congViec = null;
        c.close();
        db.close();
        return congViec;
    }*/
   public int deleteCongViec(int id) {
       int ret = -1;
       SQLiteDatabase db = this.getWritableDatabase();
       ret=db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{id + ""});
       db.close();
       return ret;
   }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = " CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL,"
                + COLUMN_TITLE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT, " +COLUMN_DATE + " TEXT, "
                + COLUMN_HOUR + "  TEXT " + " )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME);
        onCreate(db);
    }

}
