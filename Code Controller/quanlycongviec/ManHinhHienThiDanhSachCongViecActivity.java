package vn.edu.android.quanlycongviec;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.android.adapter.CongViecAdapter;
import vn.edu.android.database.DatabaseHandler;
import vn.edu.android.model.CongViec;

public class ManHinhHienThiDanhSachCongViecActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView lvCongViec;
    List<CongViec> list;
    CongViecAdapter adapterCongViec;
    DatabaseHandler db;
    ImageButton btnThemCV;
    CongViec congViec;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_hien_thi_danh_sach_cong_viec);

        db = new DatabaseHandler(this);
        list = new ArrayList<CongViec>();
        list = db.layDanhSachCongViec();
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnThemCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyThemCongViec();
            }
        });
        lvCongViec.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteCongViec(list.get(position).getId());
                return true;
            }
        });
        lvCongViec.setOnItemClickListener(this);
    }

    private void deleteCongViec(final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn xóa không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHandler db = new DatabaseHandler(ManHinhHienThiDanhSachCongViecActivity.this);
                db.deleteCongViec(id);
                refreshListCongViecData();
            }
        });
        builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Khong lam gi het
            }
        });
        builder.create().show();
    }


    private void xuLyThemCongViec() {
        Intent intent = new Intent(ManHinhHienThiDanhSachCongViecActivity.this, ManHinhThemCongViecActivity.class);
        startActivity(intent);
    }

    private void addControls() {
        btnThemCV = (ImageButton) findViewById(R.id.btnThemCV);
        lvCongViec = (ListView) findViewById(R.id.lvCongViec);
        adapterCongViec = new CongViecAdapter(ManHinhHienThiDanhSachCongViecActivity.this,
                R.layout.item, list);
        lvCongViec.setAdapter(adapterCongViec);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CongViec congViec = this.list.get(position);
        Intent intent = new Intent(ManHinhHienThiDanhSachCongViecActivity.this, ManHinhChinhSuaCongViecActivity.class);
        intent.putExtra(DatabaseHandler.COLUMN_ID, congViec.getId());
        startActivity(intent);

    }

    public void refreshListCongViecData() {
        DatabaseHandler db = new DatabaseHandler(this);
        list.clear();
        list.addAll(db.layDanhSachCongViec());
        adapterCongViec.notifyDataSetChanged();
    }

}

