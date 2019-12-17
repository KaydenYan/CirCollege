package com.example.funnews;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class RegisterActivity extends AppCompatActivity {

    //private String realCode;
    private DBOpenHelper mDBOpenHelper;
    private TextView yzm1;
    private EditText pnumber1;
    private EditText yzmhq1;
    private ImageView dl;
    private CustomeOnClickListener listener;
    //private int realcode = 123456;//(int) ((Math.random() * 9 + 1) * 100000);
    private String realcode="=---==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        ButterKnife.bind(this);
        mDBOpenHelper = new DBOpenHelper(this);
        getView();
        registListener();






        //将验证码用图片的形式显示出来
        //mIvRegisteractivityShowcode.setImageBitmap(Code.getInstance().createBitmap());
        //realCode = Code.getInstance().getCode().toLowerCase();


        //提示用户名输入问题
        mEtRegisteractivityUsername.setOnFocusChangeListener(new
                                                                     OnFocusChangeListener() {
                                                                         @Override
                                                                         public void onFocusChange(View v, boolean hasFocus) {
                                                                             if (hasFocus) {

                                                                             } else {
                                                                                 // 此处为失去焦点时的处理内容
                                                                                 String username = mEtRegisteractivityUsername.getText().toString().trim();
                                                                                 Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
                                                                                 Matcher m = p.matcher(username);
                                                                                 if (m.find()) {
                                                                                     check1.setText("用户名中不能包含中文!");
                                                                                 }else {
                                                                                     check1.setText(null);
                                                                                 }
                                                                             }
                                                                         }
                                                                     });

        //提示密码输入问题
        mEtRegisteractivityPassword1.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                if (hasFocus) {

                } else {
                    // 此处为失去焦点时的处理内容
                    String password = mEtRegisteractivityPassword1.getText().toString().trim();
                    if(password.length()<6 || password.length()>16){
                        check2.setText("您输入的密码应在6-16位之间!");
                    }else{
                        check2.setText(null);
                    }

                }

            }
        });


        mEtRegisteractivityPassword2.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                if (hasFocus) {

                } else {
                    // 此处为失去焦点时的处理内容
                    String password = mEtRegisteractivityPassword1.getText().toString().trim();
                    String password2 = mEtRegisteractivityPassword2.getText().toString().trim();

                    if(!password.equals(password2)){
                        check2.setText("您两次输入的密码不一致!");
                    }else{
                        check2.setText(null);
                    }
                }
            }
        });





    }

    //使代码简单易读

    @BindView(R.id.et_registeractivity_username)
    EditText mEtRegisteractivityUsername;
    @BindView(R.id.et_registeractivity_password1)
    EditText mEtRegisteractivityPassword1;
    @BindView(R.id.et_registeractivity_password2)
    EditText mEtRegisteractivityPassword2;

    @BindView(R.id.usrerr)
    TextView check1;
    @BindView(R.id.psderr)
    TextView check2;



    public void getView(){
        pnumber1 = findViewById(R.id.pnumber);
        yzm1 = findViewById(R.id.yzm);
        dl=findViewById(R.id.bt_registeractivity_register);
    }
    private void registListener(){
        listener = new CustomeOnClickListener();
        pnumber1.setOnClickListener(listener);
        yzm1.setOnClickListener(listener);
        dl.setOnClickListener(listener);
    }
    public void sendRequestWithOkHttp(final String ab) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 创建一个OkHttpClient的实例
                    OkHttpClient client = new OkHttpClient();
                    // 如果要发送一条HTTP请求，就需要创建一个Request对象
                    // 可在最终的build()方法之前连缀很多其他方法来丰富这个Request对象
                    Request request = new Request.Builder()
                            .url(ab)
                            .build();
                    // 调用OkHttpClient的newCall()方法来创建一个Call对象，并调用execute()方法来发送请求并获取服务器的返回数据
                    Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    class CustomeOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.yzm:
                    int qwe=(int)((Math.random() * 9 + 1) * 100000);
                    realcode=""+qwe;
                    String Pnumber = pnumber1.getText().toString().trim();
                    String aac="http://v.juhe.cn/sms/send?mobile="+Pnumber+"&tpl_id=201577&tpl_value=%23code%23%3D"+realcode+"&dtype=&key=376a8669319ed3b6811f12065d084c58";
                    sendRequestWithOkHttp(aac);
                    break;
                case R.id.bt_registeractivity_register:    //注册按钮
                    //获取用户输入的用户名、密码、验证码
                    String username = mEtRegisteractivityUsername.getText().toString().trim();
                    String password = mEtRegisteractivityPassword1.getText().toString().trim();
                    String password2 = mEtRegisteractivityPassword2.getText().toString().trim();
                    yzmhq1 = findViewById(R.id.yzmhq);
                    String phoneCode = yzmhq1.getText().toString().trim();

                    //判断用户名是否包含中文
                    Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
                    Matcher m = p.matcher(username);
                    //注册验证
                    if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)  ) {
                        if (phoneCode.equals(realcode)) {
                            //判断用户名中是否含有中文
                            if (m.find()) {
                                Toast.makeText(RegisterActivity.this,  "用户名中不能包含中文!", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            if(!password.equals(password2)){
                                Toast.makeText(RegisterActivity.this,  "两次输入的密码不一致!", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            if(password.length()<6 || password.length()>16){
                                Toast.makeText(RegisterActivity.this,  "您输入的密码应在6-16位之间!", Toast.LENGTH_SHORT).show();
                                break;
                            }


                            //将用户名和密码加入到数据库中
                            mDBOpenHelper.add(username, password);
                            Intent intent2 = new Intent(RegisterActivity.this, loginActivity.class);
                            startActivity(intent2);
                            finish();
                            Toast.makeText(RegisterActivity.this,  "验证通过，注册成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "验证码错误,注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this, "未完善信息，注册失败", Toast.LENGTH_SHORT).show();
                    }
                    break;

            }
        }
    }


}
