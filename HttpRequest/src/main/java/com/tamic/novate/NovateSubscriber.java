/*
 *    Copyright (C) 2016 Tamic
 *
 *    link :https://github.com/Tamicer/Novate
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.tamic.novate;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.tamic.novate.constant.BroadcastAction;
import com.tamic.novate.exception.NovateException;
import com.tamic.novate.exception.ServerException;
import com.tamic.novate.util.LogWraper;
import com.tamic.novate.util.ReflectionUtil;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

import static com.tamic.novate.Novate.TAG;

/**
 * NovateSubscriber
 * Created by Tamic on 2016-06-06.
 *
 * @param <T>
 */
class NovateSubscriber<T> extends BaseSubscriber<ResponseBody> {

    //Gson gson = new Gson();
    private Novate.ResponseCallBack<T> callBack;
    private Context context;

    private Type finalNeedType;
    //private Type dataType;

    public NovateSubscriber(Context context, Novate.ResponseCallBack callBack) {
        super(context);
        this.callBack = callBack;
        this.context = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        //dataType = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Type[] types = ReflectionUtil.getParameterizedTypeswithInterfaces(callBack);
        if (ReflectionUtil.methodHandler(types) == null || ReflectionUtil.methodHandler(types).size() == 0) {
            LogWraper.e(TAG, "callBack<T> 中T不合法: -->" + finalNeedType);
            throw new NullPointerException("callBack<T> 中T不合法");
        }
        finalNeedType = ReflectionUtil.methodHandler(types).get(0);
        // todo some common as show loadding  and check netWork is NetworkAvailable
        if (callBack != null) {
            callBack.onStart();
        }

    }

    @Override
    public void onCompleted() {
        // todo some common as  dismiss loadding
        if (callBack != null) {
            callBack.onCompleted();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (callBack != null) {
            callBack.onError(e);
        }
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        try {
            byte[] bytes = responseBody.bytes();
            String jsStr = new String(bytes);
            LogWraper.d("Novate", "ResponseBody:" + jsStr.trim());
            if (callBack != null) {

                /**
                 * if need parse baseRespone<T> use ParentType, if parse T use childType . defult parse baseRespone<T>
                 *
                 *  callBack.onSuccee((T) JSON.parseArray(jsStr, (Class<Object>) finalNeedType));
                 *  Type finalNeedType = needChildType;
                 *  这里只是把返回的JSON中的data属性转换成实体类
                 */
                boolean success = false;
                String msg = "";
                int state;
                String dataStr = "";
                T dataResponse = null;
                NovateResponse<T> baseResponse = null;
                try {
                    baseResponse = new NovateResponse<>();
                    JSONObject jsonObject = new JSONObject(jsStr.trim());
                    /**
                     * success 为true 的时候 才会成功
                     */
                    success = jsonObject.optBoolean("success");
                    state = jsonObject.optInt("state");
                    msg = jsonObject.optString("msg");
                   /* if(state == 401){
                            if(null != this.context){
                                Intent intent = new Intent(BroadcastAction.ACTION_LONOUT_403_SUCCEED);
                                intent.putExtra(BroadcastAction.STATE,state);
                                intent.putExtra(BroadcastAction.MESSAGE,msg);
                                context.sendBroadcast(intent);
                            }
                    }else {*/
                        baseResponse.setSuccess(success);
                        baseResponse.setMsg(msg);
                        baseResponse.setState(state);
                        if (success) {
                            dataStr = jsonObject.optString("data");
                            if (!TextUtils.isEmpty(dataStr) && !"null".equals(dataStr)) {
                                dataResponse = (T) new Gson().fromJson(dataStr, ReflectionUtil.newInstance(finalNeedType).getClass());
                                //dataResponse = (T) gson.fromJson(dataStr, dataType);
                            /*if (null == dataResponse) {
                                if (callBack != null) {
                                    callBack.onError(NovateException.handleException(new ServerException(NovateResponse.EMPTY_STATE, "数据为空")));
                                }
                            }*/
                            }/*else{
                            if (callBack != null) {
                                callBack.onError(NovateException.handleException(new ServerException(NovateResponse.EMPTY_STATE, "数据为空")));
                            }
                        }*/


                            /**
                             * 成功回调
                             */
                            baseResponse.setData(dataResponse);
                            callBack.onSuccess(state, msg, dataResponse, jsStr);
                            //callBack.onSuccee(baseResponse);

                        } else {
                            if (callBack != null) {
                                callBack.onError(NovateException.handleException(new ServerException(state, msg)));
                            }
                        }
                    /*}*/
                } catch (Exception e) {
                    e.printStackTrace();
                    LogWraper.e(TAG, e.getLocalizedMessage());
                    if (callBack != null) {
                        callBack.onError(NovateException.handleException(e));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (callBack != null) {
                callBack.onError(NovateException.handleException(e));
            }
        }
    }
}