package com.emcc.barcode.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.emcc.barcode.manager.ActivityManager;

public abstract class BaseActivity extends AppCompatActivity {

    /*context*/
    protected Context mContext = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        getIntentData();
        // activity manager
        ActivityManager.getInstance().addActivity(this);

        setContentView(getLayoutID());


        initView();

    }

    /*获取显示页面ID*/
    protected abstract int getLayoutID();

    /*获取页面传递数据*/
    protected abstract void getIntentData();

    protected abstract void initView();

    protected void readyGo(Class<?> clazz) {
        Intent intent = getGoIntent(clazz);
        startActivity(intent);
    }
    /*带数据传参*/
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = getGoIntent(clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected Intent getGoIntent(Class<?> clazz) {
        return new Intent(this, clazz);
    }

    @Override
    public void finish() {
        super.finish();
        ActivityManager.getInstance().removeActivity(this);
    }
}


