package vn.edu.android.quanlycongviec;

import android.content.DialogInterface;
import android.content.Intent;
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

public class ManHinhHienThiDanhSachCongViecActivity extends AppCompatActivity {

    public static final long NEW_NOTE = -1;
    public static final String ID = "ID";
    private CongViec cv;

    ListView lvCongViec;
    List<CongViec> list;
    CongViecAdapter adapterCongViec;
    DatabaseHandler db;
    ImageButton btnThemCV;

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
        lvCongViec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CongViec congViec = list.get(position);
                Intent intent = new Intent(ManHinhHienThiDanhSachCongViecActivity.this,ManHinhChinhSuaCongViecActivity.class);
                intent.putExtra(DatabaseHandler.COLUMN_ID,congViec.getId());
                startActivity(intent);
            }
        });
        lvCongViec.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {

               final CongViec congViec = list.get(position);
               AlertDialog.Builder builder = new AlertDialog.Builder(ManHinhHienThiDanhSachCongViecActivity.this);
                builder.setTitle("Xóa công việc");
                builder.setIcon(android.R.drawable.ic_delete);
               builder.setMessage("Bạn có chắc xóa?");
               builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                        db = new DatabaseHandler(getApplicationContext());
                        db.deleteCongViec((int) id);
                        list.remove(congViec);
                        list.clear();
                        adapterCongViec.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Khong lam gi ca
                    }
                });
                builder.create().show();
                return true;
            }
        });
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
}

