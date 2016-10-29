package vn.edu.android.baitaplab11_nhom5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ManHinhHienThiActivity extends AppCompatActivity {
    TextView txtkq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_hien_thi);

        txtkq = (TextView) findViewById(R.id.txtKq);
        Intent intent=getIntent();
        String kieuchuoi= intent.getStringExtra("KIEUCHUOI");
        txtkq.setText("Kết quả:"+kieuchuoi);
    }
}
