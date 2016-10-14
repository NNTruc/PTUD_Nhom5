package vn.edu.android.ptudnhom5demform;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText txtID,txtName;
    Button btnAdd,btnPrint;
    SharedPreferences ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ref=this.getSharedPreferences("vn.edu.android.ptudnhom5demoform", Context.MODE_PRIVATE);
        ref.edit().clear().commit();

        addControls();
        addEvents();
    }

    private void addEvents() {
        ref.edit().putString(txtID.getText().toString(),txtName.getText().toString()).commit();
        txtID.setText("");
        txtName.setText("");


    }

    private void addControls() {
        txtID = (EditText) findViewById(R.id.txtID);
        txtName = (EditText) findViewById(R.id.txtName);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnPrint = (Button) findViewById(R.id.btnPrint);
    }
}
