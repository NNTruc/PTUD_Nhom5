package vn.edu.android.quanlycongviec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.android.database.DatabaseHelper;
import vn.edu.android.model.ThongTin;

public class ManHinhDangKyActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_ky);
    }
    public void XuLyDangKy(View v) {
        if(v.getId() == R.id.btnDangKy)
        {
            EditText txtTenDangKy = (EditText) findViewById(R.id.txtTenDangKy);
            EditText txtMatKhauDangyKy = (EditText) findViewById(R.id.txtMatKhauDangKy);
            EditText txtNhapLaiMatKhau = (EditText) findViewById(R.id.txtNhapLaiMKDK);

            String namestr = txtTenDangKy.getText().toString();
            String passstr = txtMatKhauDangyKy.getText().toString();
            String nhaplaistr = txtNhapLaiMatKhau.getText().toString();

            if(!passstr.equals(nhaplaistr))
            {
                Toast.makeText(ManHinhDangKyActivity.this,"Nhập lại mật khẩu sai", Toast.LENGTH_LONG).show();
            }
            else{
                Intent intent =new Intent(ManHinhDangKyActivity.this,ManHinhDangNhapActivity.class);
                ThongTin thongTin =new ThongTin();
                thongTin.setUsername(namestr);
                thongTin.setPassword(passstr);
                databaseHelper.insertThongTin(thongTin);
                Toast.makeText(ManHinhDangKyActivity.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }

        }
    }
}
