package vn.edu.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtPhepTinh,txtKetQua;
    Button btnC,btnTru,btnCong,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnBang;
    String strMath="";
    Boolean bCo = false, bCo2 = false, bCoDauPhay=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnC.setOnClickListener(new ProcessMyEvent());
        btnCong.setOnClickListener(new ProcessMyEvent());
        btnTru.setOnClickListener( new ProcessMyEvent());
        btnBang.setOnClickListener( new ProcessMyEvent());
        btn0.setOnClickListener( new ProcessMyEvent());
        btn1.setOnClickListener(new ProcessMyEvent());
        btn2.setOnClickListener(new ProcessMyEvent());
        btn3.setOnClickListener(new ProcessMyEvent());
        btn4.setOnClickListener(new ProcessMyEvent());
        btn5.setOnClickListener(new ProcessMyEvent());
        btn6.setOnClickListener(new ProcessMyEvent());
        btn7.setOnClickListener(new ProcessMyEvent());
        btn8.setOnClickListener(new ProcessMyEvent());
        btn9.setOnClickListener(new ProcessMyEvent());
    }

    private void addControls() {
        txtPhepTinh = (TextView) findViewById(R.id.txtPhepTinh);
        txtKetQua = (TextView) findViewById(R.id.txtKetQua);
        btn0= (Button) findViewById(R.id.btn0);
        btn1= (Button) findViewById(R.id.btn1);
        btn2= (Button) findViewById(R.id.btn2);
        btn3= (Button) findViewById(R.id.btn3);
        btn4= (Button) findViewById(R.id.btn4);
        btn5= (Button) findViewById(R.id.btn5);
        btn6= (Button) findViewById(R.id.btn6);
        btn7= (Button) findViewById(R.id.btn7);
        btn8= (Button) findViewById(R.id.btn8);
        btn9= (Button) findViewById(R.id.btn9);
        btnBang= (Button) findViewById(R.id.btnBang);
        btnC= (Button) findViewById(R.id.btnC);
        btnTru= (Button) findViewById(R.id.btnTru);
        btnCong= (Button) findViewById(R.id.btnCong);
    }

    private class ProcessMyEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            try {

                switch (v.getId()) {
                    case R.id.btn0:
                        // code
                        bCo=false;
                        bCo2=false;
                        strMath +="0";
                        ShowMath(strMath);
                        break;
                    case R.id.btn1:
                        // code
                        bCo=false;
                        bCo2=false;
                        strMath +="1";
                        ShowMath(strMath);
                        break;
                    case R.id.btn2:
                        // code
                        bCo=false;
                        bCo2=false;
                        strMath +="2";
                        ShowMath(strMath);
                        break;
                    case R.id.btn3:
                        // code
                        bCo=false;
                        bCo2=false;
                        strMath +="3";
                        ShowMath(strMath);
                        break;
                    case R.id.btn4:
                        // code
                        bCo=false;
                        bCo2=false;
                        strMath +="4";
                        ShowMath(strMath);
                        break;
                    case R.id.btn5:
                        // code
                        bCo=false;
                        bCo2=false;
                        strMath +="5";
                        ShowMath(strMath);
                        break;
                    case R.id.btn6:
                        // code
                        bCo=false;
                        bCo2=false;
                        strMath +="6";
                        ShowMath(strMath);
                        break;
                    case R.id.btn7:
                        // code
                        bCo=false;
                        bCo2=false;
                        strMath +="7";
                        ShowMath(strMath);
                        break;
                    case R.id.btn8:
                        // code
                        bCo=false;
                        bCo2=false;
                        strMath +="8";
                        ShowMath(strMath);
                        break;
                    case R.id.btn9:
                        // code
                        bCo=false;
                        bCo2=false;
                        strMath +="9";
                        ShowMath(strMath);
                        break;
                    case R.id.btnC:
                        // code
                        bCo=false;
                        bCo2=false;
                        strMath ="";
                        ShowMath(strMath);
                        txtKetQua.setText("");
                        break;
                    case R.id.btnCong:
                        bCoDauPhay=false;
                        if(!KT()){// KT1 là hàm kiểm tra ký tự nhập đầu tiên có phải là phép tính ko. nếu đúng là phép tính thì ko cho nhập
                            if(bCo2 ==true){// kiểm tra có phải ký tự vừa nhập trước đó phải là phép tính không. nếu phải thì thay thế phép tính đó bằng phép tính hiện tại.
                                Replace(" + ");// hàm thay thế ký tự phép tính cuối chuỗi
                                ShowMath(strMath);
                                if(bCo==false){// kiểm tra xem phép tính nhập lần thứ mấy. nếu lần thứ 1 thì cho nhập.
                                    strMath +=" + ";
                                    ShowMath(strMath);
                                    bCo=true;//sau khi nhập phép tính vào thì bật cờ của phép tính đó lên.
                                    bCo2=true;// bật cờ báo hiệu là ký tự cuối vừa nhập là 1 phép tính
                                }

                            }else{// kiểm tra có phải ký tự vừa nhập trước đó phải là phép tính không. nếu không phải thì nhập vào phép tính hiện tại.
                                if(bCo==false){
                                    strMath +=" + ";
                                    ShowMath(strMath);
                                    bCo=true;
                                    bCo2=true;
                                }
                            }
                        }
                        break;
                    case R.id.btnTru:
                        bCoDauPhay=false;
                        if(!KT()){// KT1 là hàm kiểm tra ký tự nhập đầu tiên có phải là phép tính ko. nếu đúng là phép tính thì ko cho nhập
                            if(bCo2 ==true){// kiểm tra có phải ký tự vừa nhập trước đó phải là phép tính không. nếu phải thì thay thế phép tính đó bằng phép tính hiện tại.
                                Replace(" - ");// hàm thay thế ký tự phép tính cuối chuỗi
                                ShowMath(strMath);
                                if(bCo==false){// kiểm tra xem phép tính nhập lần thứ mấy. nếu lần thứ 1 thì cho nhập.
                                    strMath +=" - ";
                                    ShowMath(strMath);
                                    bCo=true;//sau khi nhập phép tính vào thì bật cờ của phép tính đó lên.
                                    bCo2=true;// bật cờ báo hiệu là ký tự cuối vừa nhập là 1 phép tính
                                }

                            }else{// kiểm tra có phải ký tự vừa nhập trước đó phải là phép tính không. nếu không phải thì nhập vào phép tính hiện tại.
                                if(bCo==false){
                                    strMath +=" - ";
                                    ShowMath(strMath);
                                    bCo=true;
                                    bCo2=true;
                                }
                            }
                        }
                        break;
                    case R.id.btnBang:
                        // code
                        bCo=false;
                        bCo2=false;
                        bCoDauPhay=false;
                        //TinhKetQua();
                        if(strMath!=""){
                            txtKetQua.setText("= " + TinhKetQua().toString());
                        }
                        break;
                }
            } catch (Exception e) {
                // TODO: handle exception
                Toast.makeText(MainActivity.this, "Lỗi chưa xác định", Toast.LENGTH_LONG).show();
                txtKetQua.setText("= Math ERROR!");
            }
        }
    }


    private void ShowMath(String strMath){
        txtPhepTinh.setText(strMath);
    }

    private Boolean KT(){
        if(strMath==""){
            return true;
        }else {
            return false;
        }
    }
    private void Replace(String str){
        strMath=strMath.substring(0, strMath.length()-3)+ str;
    }
    private Double TinhKetQua(){
        String Mang[];
        double dKetQua = 0, temp = 1;
        Mang = strMath.split(" ");
        temp = Double.parseDouble(Mang[0]);
        // thuật giải này tham khảo tại đây : http://stackoverflow.com/questions/10280263/how-do-a-simple-calculator-in-android-how-to-do-it-using-buttons-and-single-edi
		/*-- NỘI DUNG CÁCH TÍNH 1 CHUỖI
		 * 2 * 4 + 3 + 5 * 2 * 3 - 1 + 2
			initially final result = 0, buffer = 1

			2 -> result = 0, buffer = 2 (careful here, I interpreted the initial step as *2)

			* 4 -> result = 0, buffer = (2*4) = 8
			+ 3 -> result = (0+8) = 8, buffer = 3
			+ 5 -> result = (8+3) = 11, buffer = 5
			* 2 -> result = 11, buffer = (5*2) = 10
			* 3 -> result = 11, buffer = (10*3) = 30
			- 1 -> result = (11+30) = 41, buffer = -1
			+ 2 -> result = (41-1) = 40, buffer = 2
			-> finally (e.g. when pressing =) add both together: Answer = 42
		 */
        for(int i=1; i<Mang.length; i++){
            if(Mang[i].equals("+")){
                dKetQua = temp + dKetQua;
                temp = Double.parseDouble(Mang[i+1]);
                //i++;
            }
            if(Mang[i].equals("-")){
                dKetQua = temp - dKetQua;
                temp = -Double.parseDouble(Mang[i+1]);
                //++;
            }
        }
       return dKetQua + temp;
    }
}
