package com.emcc.barcode;

import android.app.Application;

import com.emcc.barcode.utils.http.HttpRequestUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
        HttpRequestUtils.init(this);
    }
}
