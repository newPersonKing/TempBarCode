package com.emcc.barcode.activity;

import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.emcc.barcode.R;
import com.emcc.barcode.base.BaseActivity;

/*用来接收 扫码枪扫描的信息*/
public class ReceviceMessageActivity extends BaseActivity implements View.OnKeyListener {

    EditText et_recevice;
    TextView tv_save;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_recevice_message;
    }

    @Override
    protected void getIntentData() {

    }

    @Override
    protected void initView() {
        et_recevice = findViewById(R.id.et_recevice);
        tv_save = findViewById(R.id.tv_save);

        et_recevice.setOnKeyListener(this);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER){
            String text = et_recevice.getText().toString().trim();
            if(!text.isEmpty()&&text!=""){
                tv_save.setText(text);
            }
            et_recevice.setText("");
        }
        return false;
    }
}
