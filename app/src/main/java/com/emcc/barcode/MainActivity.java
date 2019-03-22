package com.emcc.barcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.emcc.barcode.utils.http.HttpRequestUtils;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.ResponseCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HashMap<String,Object> map = new HashMap<>();
        map.put("email","admin");
        map.put("password","admin");
        HttpRequestUtils.getInstance().getNovate().rxPost("rest/login", map, new ResponseCallback<Object, ResponseBody>() {
            @Override
            public Object onHandleResponse(ResponseBody response) throws Exception {
                return null;
            }

            @Override
            public void onError(Object tag, Throwable e) {

            }

            @Override
            public void onCancel(Object tag, Throwable e) {

            }

            @Override
            public void onNext(Object tag, Call call, Object response) {

            }
        });
    }
}
