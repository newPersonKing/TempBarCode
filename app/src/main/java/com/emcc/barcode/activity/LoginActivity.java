package com.emcc.barcode.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emcc.barcode.MainActivity;
import com.emcc.barcode.R;
import com.emcc.barcode.base.BaseActivity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    EditText et_login_main_username;
    EditText et_login_password;
    Button btn_login_in;
    Button btn_fast_login_in;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void getIntentData() {

    }

    @Override
    protected void initView() {
        et_login_main_username = findViewById(R.id.et_login_main_username);
        et_login_password = findViewById(R.id.et_login_password);
        btn_login_in = findViewById(R.id.btn_login_in);
        btn_fast_login_in = findViewById(R.id.btn_fast_login_in);
        btn_fast_login_in.setOnClickListener(this);
        btn_login_in.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fast_login_in:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.btn_login_in:
                readyGo(MainActivity.class);
                break;
        }
    }
}
