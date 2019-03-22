package com.emcc.barcode.utils.http;

import android.content.Context;

import com.tamic.novate.BuildConfig;
import com.tamic.novate.Novate;

public class HttpRequestUtils {

    private static final int CONNECT_TIME_OUT = 60;
    private static final int WRITE_TIME_OUT = 60;
    private static final int READ_TIME_OUT = 60;
    private String BASE_URL="http://172.26.163.31:9020";
    private static HttpRequestUtils instance;
    private Novate novate;
    private static Context mContext;
    public static void init(Context context){
        mContext = context;
    }

    public static  HttpRequestUtils getInstance(){
        if(instance == null){
            synchronized (HttpRequestUtils.class){
                if(instance == null){
                    instance = new HttpRequestUtils();
                }
            }
        }
        return instance;
    }

    private HttpRequestUtils(){
        novate = new Novate.Builder(mContext)
                .baseUrl(BASE_URL)
                .connectTimeout(CONNECT_TIME_OUT)
                .readTimeout(READ_TIME_OUT)
                .writeTimeout(WRITE_TIME_OUT)
                .addCache(false)
                .addCookie(true)
                .addLog(BuildConfig.DEBUG)
                .build();
    }

    public Novate getNovate(){
        return novate;
    }
}
