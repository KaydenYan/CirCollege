package com.example.funnews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class loginActivity extends AppCompatActivity {

    SharedPreferences sprfMain;
    SharedPreferences.Editor editorMain;

    private DBOpenHelper mDBOpenHelper;
    //private TextView tv_loginactivity_register;
    //private CustomeOnClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断登录状态
        sprfMain= PreferenceManager.getDefaultSharedPreferences(this);
        editorMain=sprfMain.edit();
        if(sprfMain.getBoolean("main",false)){
            Intent intent=new Intent(loginActivity.this,TextActivityTwo.class);
            startActivity(intent);
            loginActivity.this.finish();
        }

        setContentView(R.layout.login);
        ButterKnife.bind(this);
        mDBOpenHelper = new DBOpenHelper(this);


        //tv_loginactivity_register=findViewById(R.id.tv_loginactivity_register);
        //getView();
        //registListener();
    }

    @BindView(R.id.tv_loginactivity_register)
    TextView mTvLoginactivityRegister;
    @BindView(R.id.et_loginactivity_username)
    EditText mEtLoginactivityUsername;
    @BindView(R.id.et_loginactivity_password)
    EditText mEtLoginactivityPassword;

    @OnClick({
            //返回按钮
            // R.id.iv_loginactivity_back,
            R.id.bt_loginactivity_login,
            R.id.tv_loginactivity_register,
            //忘记密码点击效果
            //R.id.tv_loginactivity_forget,
            //短信验证点击效果
            //R.id.tv_loginactivity_check,

    })

    public void onClick(View view) {
        switch (view.getId()) {
            //case R.id.iv_loginactivity_back:
            //TODO 返回箭头功能
            //finish();//销毁此Activity
            //    break;
            case R.id.tv_loginactivity_register:
                // 注册界面跳转
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
            //case R.id.tv_loginactivity_forget:   //忘记密码
            //TODO 忘记密码操作界面跳转
            //startActivity(new Intent(this, FindPasswordActivity.class));
            //    break;
            //case R.id.tv_loginactivity_check:    //短信验证码登录
            // TODO 短信验证码登录界面跳转
            //startActivity(new Intent(this, MessageLoginActivity.class));
            //    finish();
            //    break;

            case R.id.bt_loginactivity_login:
                String name = mEtLoginactivityUsername.getText().toString().trim();
                String password = mEtLoginactivityPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mDBOpenHelper.getAllData();
                    boolean match = false;
                    for(int i=0;i<data.size();i++) {
                        User user = data.get(i);
                        if (name.equals(user.getName()) && password.equals(user.getPassword())){
                            match = true;
                            break;
                        }else{
                            match = false;
                        }
                    }
                    if (match) {
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, TextActivityTwo.class);
                        //记录登录状态
                        editorMain.putBoolean("main",true);
                        editorMain.commit();
                        startActivity(intent);
                        finish();//销毁此Activity
                    }else {
                        Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

}
