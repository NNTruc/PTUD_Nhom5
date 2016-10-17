package vn.edu.android.quanlycongviec;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import vn.edu.android.database.DatabaseHandler;
import vn.edu.android.model.CongViec;

public class ManHinhThemCongViecActivity extends AppCompatActivity {

    DatabaseHandler db;
    EditText txtTenCongViec,txtMota;
    TextView txtNgayHT,txtGioHT;
    ImageButton btnNgay,btnGio;
    Button btnXoaTrang,btnLuuCongViec;
    Date dateFinish,timeFinish;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_them_cong_viec);

        db = new DatabaseHandler(this);

        addControls();
        addEvents();
        getDefaultInfor();
    }

    private void addEvents() {
        btnNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLyHienThiNgay();
            }
        });
        btnGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyHienThiGio();
            }
        });
        btnLuuCongViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLuuCongViec();
            }
        });
        btnXoaTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXoaTrang();
            }
        });
    }

    private void xuLyXoaTrang() {
        txtTenCongViec.setText("");
        txtMota.setText("");
        txtTenCongViec.requestFocus();
        getDefaultInfor();
    }

    private void xuLyLuuCongViec() {
        //Kiem tra khong rong thi khong cho thuc hien
        String mId = txtTenCongViec.getText().toString().trim();
        if (mId.length() == 0) {
            txtTenCongViec.setError("?");
            txtTenCongViec.requestFocus();
            return;
        }
        String name = txtMota.getText().toString().trim();
        if (name.length() == 0) {
            txtMota.setError("?");
            txtMota.requestFocus();
            return;
        }

        Intent intent = new Intent(ManHinhThemCongViecActivity.this,ManHinhHienThiDanhSachCongViecActivity.class);
        CongViec congViec = new CongViec();
        congViec.setTenCongViec(txtTenCongViec.getText().toString());
        congViec.setMoTa(txtMota.getText().toString());
        congViec.setNgayHT(txtNgayHT.getText().toString());
        congViec.setGioHT(txtGioHT.getText().toString());
        db.themCongViec(congViec);
        startActivity(intent);
        finish();
    }

    private void xuLyHienThiGio() {
        TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Xử lý lưu giờ và AM,PM
                String s = hourOfDay + ":" + minute;
                int hourTam = hourOfDay;
                if (hourTam > 12)
                    hourTam = hourTam - 12;
                txtGioHT.setText
                        (hourTam + ":" + minute + (hourOfDay > 12 ? " PM" : " AM"));
                //lưu giờ thực vào tag
                txtGioHT.setTag(s);
                //lưu vết lại giờ vào hourFinish
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                timeFinish = cal.getTime();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong TimePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s = txtGioHT.getTag() + "";
        String strArr[] = s.split(":");
        int gio = Integer.parseInt(strArr[0]);
        int phut = Integer.parseInt(strArr[1]);
        TimePickerDialog time = new TimePickerDialog(
                ManHinhThemCongViecActivity.this,
                callback, gio, phut, true);
        time.setTitle("Chọn giờ hoàn thành");
        time.show();
    }

    private void XuLyHienThiNgay() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
                txtNgayHT.setText((dayOfMonth) + "/" + (monthOfYear + 1) + "/" + year);
                //Lưu vết lại biến ngày hoàn thành
                cal.set(year, monthOfYear, dayOfMonth);
                dateFinish = cal.getTime();
            }
        };
        //các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s = txtNgayHT.getText() + "";
        String strArrtmp[] = s.split("/");
        int ngay = Integer.parseInt(strArrtmp[0]);
        int thang = Integer.parseInt(strArrtmp[1]) - 1;
        int nam = Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic = new DatePickerDialog(
                ManHinhThemCongViecActivity.this,
                callback, nam, thang, ngay);
        pic.setTitle("Chọn ngày hoàn thành");
        pic.show();
    }

    public void getDefaultInfor() {
        //lấy ngày hiện tại của hệ thống
        cal = Calendar.getInstance();
        SimpleDateFormat dft = null;
        //Định dạng ngày / tháng /năm
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = dft.format(cal.getTime());
        //hiển thị lên giao diện
        txtNgayHT.setText(strDate);

        //Định dạng giờ phút am/pm
        dft = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String strTime = dft.format(cal.getTime());
        //đưa lên giao diện
        txtGioHT.setText(strTime);
        //lấy giờ theo 24 để lập trình theo Tag
        dft = new SimpleDateFormat("HH:mm", Locale.getDefault());
        txtGioHT.setTag(dft.format(cal.getTime()));
        txtTenCongViec.requestFocus();
        //gán cal.getTime() cho ngày hoàn thành và giờ hoàn thành
        dateFinish = cal.getTime();
        timeFinish = cal.getTime();
    }

    private void addControls() {
        txtTenCongViec = (EditText) findViewById(R.id.txtTenCongViec);
        txtMota = (EditText) findViewById(R.id.txtMota);
        txtNgayHT = (TextView) findViewById(R.id.txtNgayHT);
        txtGioHT = (TextView) findViewById(R.id.txtGioHT);
        btnNgay = (ImageButton) findViewById(R.id.btnNgay);
        btnGio = (ImageButton) findViewById(R.id.btnGio);
        btnXoaTrang = (Button) findViewById(R.id.btnXoaTrang);
        btnLuuCongViec = (Button) findViewById(R.id.btnLuuCV);
    }
}
