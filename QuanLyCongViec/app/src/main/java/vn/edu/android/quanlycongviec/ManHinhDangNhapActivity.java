package vn.edu.android.quanlycongviec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.android.database.DatabaseHelper;

public class ManHinhDangNhapActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_nhap);
    }

    public void XuLyDanhNhap(View v) {
        if(v.getId() == R.id.btnDangNhap)
        {
            EditText txtTenDangNhap = (EditText) findViewById(R.id.txtTenDangNhap);
            String users = txtTenDangNhap.getText().toString();
            EditText txtMatKhau = (EditText) findViewById(R.id.txtMatkhauDangNhap);
            String pass = txtMatKhau.getText().toString();

            String password = databaseHelper.searchMatKhau(users);
            if(pass.equals(password))
            {
                Intent intent = new Intent(ManHinhDangNhapActivity.this,ManHinhHienThiDanhSachCongViecActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(ManHinhDangNhapActivity.this,"Lỗi sai tên đăng nhâp hoặc mật khẩu",Toast.LENGTH_LONG).show();
            }

        }

    }

    public void XuLyDangKyTaiKhoan(View view) {
        Intent intent = new Intent(ManHinhDangNhapActivity.this,ManHinhDangKyActivity.class);
        startActivity(intent);
    }
}