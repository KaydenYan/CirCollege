package com.example.funnews;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
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
        setStatusBar();


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
                        //记录用户名
                        Bundle bundle = new Bundle();
                        bundle.putString("nameUser",name);
                        intent.putExtras(bundle);
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

    protected void setStatusBar() {//状态栏沉浸，状态栏颜色，状态栏系统图标的深浅色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (false) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorTheme));//设置状态栏背景色
            } else {
                getWindow().setStatusBarColor(Color.TRANSPARENT);//透明
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {
            //Toast.makeText(this, "低于4.4的android系统版本不存在沉浸式状态栏", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && true) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

}
