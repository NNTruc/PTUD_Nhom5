package vn.edu.android.baitap_lab11_nhom5_bt2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtNhapChuoi;
    Button btnEnter,btnExit;
    Context context;

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
                xuLy();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void xuLy() {
        int n = nhap();
        lietKeSNT(n);

    }

    private void addControls() {
        txtNhapChuoi= (EditText) findViewById(R.id.txtNhapChuoi);
        btnEnter= (Button) findViewById(R.id.btnEnter);
        btnExit= (Button) findViewById(R.id.btnExit);
    }

    public int nhap()
    {
        boolean check = false;
        int n = 0;
        while (!check) {
            Toast.makeText(MainActivity.this," ",Toast.LENGTH_SHORT).show();
            try {
                n = Integer.parseInt(txtNhapChuoi.getText().toString());
                check = true;
            } catch (Exception e) {
                Toast.makeText(MainActivity.this,"Ban phai nhap so,Nhap lai",Toast.LENGTH_SHORT).show();
                n = Integer.parseInt(txtNhapChuoi.getText().toString());
            }
        }
        return (n);
    }
    public static boolean checkSNT(int n) {
        if (n > 1) {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0)
                    return false;
            }
            return true;
        } else
            return false;
    }

    public void lietKeSNT(int n) {
        int i = 1, count = 0;
        //System.out.println("Cac so nguyen to nho hon " + n + " la: ");
        String msg="";
         msg="Cac so nguyen to nho hon " + n + "la:";
      Toast.makeText(MainActivity.this,"Cac so nguyen to nho hon " + n +" la:",Toast.LENGTH_SHORT).show();
        while (i < n) {
            if (checkSNT(i)) {
                //System.out.print(" " + i);
                Toast.makeText(MainActivity.this," "+i,Toast.LENGTH_SHORT).show();
                count++;
            }
            i++;
        }
        //System.out.println("\n Co " + count + " so thoa man");
        Toast.makeText(MainActivity.this,"\n Co " + count + "so thoa man",Toast.LENGTH_SHORT).show();
        msg="\n Cac so nguyen to nho hon "+ n + "la:" + " " + i + "\n Co"+count+"so thoa man";
        Intent intent = new Intent(MainActivity.this,ManHinhHienThiActivity.class);
        intent.putExtra("KIEUCHUOI",msg);
        startActivity(intent);
    }
}
