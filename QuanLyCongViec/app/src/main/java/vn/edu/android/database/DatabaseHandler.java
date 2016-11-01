package vn.edu.android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.android.model.CongViec;

/**
 * Created by Nguyen Trung Truc on 10/13/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "CongViec.db";
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
        SQLiteDatabase db = this.getReadableDatabase();
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

    public CongViec getCongViec(int rowID) {
        CongViec congViec;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + COLUMN_ID + " = " + "'"+ rowID + "'";
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst()){
            String tieude = c.getString(c.getColumnIndex(COLUMN_TITLE));
            String mota = c.getString(c.getColumnIndex(COLUMN_DESCRIPTION));
            String ngayHT=c.getString(c.getColumnIndex(COLUMN_DATE));
            String gioHT=c.getString(c.getColumnIndex(COLUMN_HOUR));
            congViec = new CongViec(rowID,tieude,mota,ngayHT,gioHT);
        }else
            congViec = null;
        c.close();
        close();
        return congViec;
    }

    public void updateCongViec(CongViec congViec){
        open();
        ContentValues row = new ContentValues();
        row.put(COLUMN_TITLE,congViec.getTenCongViec());
        row.put(COLUMN_DESCRIPTION,congViec.getMoTa());
        row.put(COLUMN_DATE,congViec.getNgayHT());
        row.put(COLUMN_HOUR,congViec.getGioHT());
        db.update(TABLE_NAME,row,COLUMN_ID + " = ? ",new String[]{String.valueOf(congViec.getId())});
        close();
    }

    public void deleteCongViec(int rowID){
        open();
        db.delete(TABLE_NAME,COLUMN_ID + " = ?",new String[]{String.valueOf(rowID)});
        close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = " CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + COLUMN_TITLE + " TEXT NOT NULL, " + COLUMN_DESCRIPTION + " TEXT NOT NULL, " +COLUMN_DATE + " TEXT NOT NULL, "
                + COLUMN_HOUR + "  TEXT NOT NULL " + " )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME);
        onCreate(db);
    }
    /*open database*/
    public void open(){
        try{
            db = getWritableDatabase();
        }catch (Exception e){
            Log.e("DB ERROR",e.toString());
            e.printStackTrace();
        }
    }
    /*close database*/
    public void close()
    {
        if(db!=null && db.isOpen()){
            try{
                db.close();
            }catch (Exception e){
                Log.e("DB ERROR",e.toString());
                e.printStackTrace();
            }
        }
    }

}
