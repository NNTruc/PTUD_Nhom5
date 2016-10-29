package vn.edu.android.baitaplab11_nhom5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    EditText txtChuoi;
    Button btnEnter,btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyEnter();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void xuLyEnter() {
        timXauMax();
    }

    private void addControls() {
        txtChuoi = (EditText) findViewById(R.id.txtChuoi);
        btnEnter = (Button) findViewById(R.id.btnEnter);
        btnExit = (Button) findViewById(R.id.btnExit);
    }
    public void timXauMax()
    {
        String str= txtChuoi.getText().toString();
        StringTokenizer strToken = new StringTokenizer(str, " ,\t,\r");
        int Max, i = 1, lengthStr;
        Max = strToken.nextToken().length();
        int viTriMax = i;
        while (strToken.hasMoreTokens()) {
            lengthStr = strToken.nextToken().length();
            i++;
            if (Max < lengthStr) {
                Max = lengthStr;
                viTriMax = i;
            }
        }
        Toast.makeText(MainActivity.this,"Độ dài dài lớn nhất la:" + Max + "ở vị trí:" + viTriMax,Toast.LENGTH_LONG ).show();
        String msg="Độ dài lớn nhất là:" + Max + "ở vị trí:" + viTriMax;
        Intent intent = new Intent(MainActivity.this,ManHinhHienThiActivity.class);
        intent.putExtra("KIEUCHUOI",msg);
        startActivity(intent);
    }
}
