package com.emcc.barcode.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.emcc.barcode.R;


public abstract class BaseTitleActivity extends BaseActivity{

    ImageView iv_left;
    TextView tv_left;
    TextView tv_title;
    ImageView iv_right;
    TextView tv_right;

    @Override
    protected void initView() {
        iv_left = findViewById(R.id.iv_left);
        iv_right = findViewById(R.id.iv_right);
        tv_right = findViewById(R.id.tv_right);
        tv_left = findViewById(R.id.tv_left);
        tv_title = findViewById(R.id.title);
    }

    protected void setIv_left(int resId){
        iv_left.setVisibility(View.VISIBLE);
        iv_left.setImageResource(resId);
    }

    protected void setTv_left(String text){
        tv_left.setVisibility(View.VISIBLE);
        tv_left.setText(text);
    }

    protected void setTitle(String title){
        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText(title);
    }

    protected void setIv_right(int resId){
        iv_right.setVisibility(View.VISIBLE);
        iv_right.setImageResource(resId);
    }

    protected void setTv_right(String text){
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText(text);
    }


}
