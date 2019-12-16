package com.example.funnews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {
    private TextView newusr;
    private CustomeOnClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        newusr=findViewById(R.id.newusr);
        getView();
        registListener();
    }
    public void getView(){
        newusr = findViewById(R.id.newusr);
    }
    private void registListener(){
        listener = new CustomeOnClickListener();
        newusr.setOnClickListener(listener);
    }
    class CustomeOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.newusr:
                    Intent intent1 = new Intent(
                            loginActivity.this,
                            RegisterActivity.class
                    );
                    startActivity(intent1);
                    break;

            }
        }
    }
}
