package com.example.funnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funnews.MainActivity;
import com.example.funnews.R;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class RegisterActivity extends AppCompatActivity {

    //private String realCode;
    private DBOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        ButterKnife.bind(this);
        mDBOpenHelper = new DBOpenHelper(this);

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


    @OnClick({
            //R.id.iv_registeractivity_back,
            //R.id.iv_registeractivity_showCode,
            R.id.bt_registeractivity_register
    })
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.iv_registeractivity_back: //返回登录页面
//                Intent intent1 = new Intent(this, loginActivity.class);
//                startActivity(intent1);
//                finish();
//                break;
//            case R.id.iv_registeractivity_showCode:    //改变随机验证码的生成
//                mIvRegisteractivityShowcode.setImageBitmap(Code.getInstance().createBitmap());
//                realCode = Code.getInstance().getCode().toLowerCase();
//                break;
            case R.id.bt_registeractivity_register:    //注册按钮
                //获取用户输入的用户名、密码、验证码
                String username = mEtRegisteractivityUsername.getText().toString().trim();
                String password = mEtRegisteractivityPassword1.getText().toString().trim();
                String password2 = mEtRegisteractivityPassword2.getText().toString().trim();
                //String phoneCode = mEtRegisteractivityPhonecodes.getText().toString().toLowerCase();

                //判断用户名是否包含中文
                Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
                Matcher m = p.matcher(username);
                //注册验证
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)  ) {
//                    if (phoneCode.equals(realCode)) {
                    //判断用户名中是否含有中文
                    if (m.find()) {
                        Toast.makeText(this,  "用户名中不能包含中文!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if(!password.equals(password2)){
                        Toast.makeText(this,  "两次输入的密码不一致!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if(password.length()<6 || password.length()>16){
                        Toast.makeText(this,  "您输入的密码应在6-16位之间!", Toast.LENGTH_SHORT).show();
                        break;
                    }


                    //将用户名和密码加入到数据库中
                    mDBOpenHelper.add(username, password);
                    Intent intent2 = new Intent(this, loginActivity.class);
                    startActivity(intent2);
                    finish();
                    Toast.makeText(this,  "验证通过，注册成功", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(this, "验证码错误,注册失败", Toast.LENGTH_SHORT).show();
//                    }
                }else {
                    Toast.makeText(this, "未完善信息，注册失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
