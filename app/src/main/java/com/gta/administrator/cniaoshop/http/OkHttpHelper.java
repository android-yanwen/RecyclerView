package com.gta.administrator.cniaoshop.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/20.
 */
public class OkHttpHelper {
    private OkHttpClient okHttpClient;

    private Gson gson;

    public OkHttpHelper() {
//        this.okHttpClient = new OkHttpClient();

        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(8000, TimeUnit.MICROSECONDS)
                .readTimeout(8000, TimeUnit.MICROSECONDS)
                .connectTimeout(8000, TimeUnit.MICROSECONDS)
                .build();

        gson = new Gson();
    }


    public static OkHttpHelper getInstance() {
        return new OkHttpHelper();
    }

    //"https://raw.github.com/square/okhttp/master/README.md"
    public void get(String url, BaseCallback callback) {
        Request request = buildRequest(url, null, HttpUrlType.GET);
        doRequest(request, callback);

    }

    public void post(String url, Map<String, String> datas, BaseCallback callback) {
        Request request = buildRequest(url, datas, HttpUrlType.POST);
        doRequest(request, callback);
    }

    private void doRequest(final Request request, final BaseCallback callback) {

        callback.onRequestBefore();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String resultStr = response.body().string();
                    if (callback.mType == String.class) {
                        callback.onSuccess(resultStr);
                    } else {
                        Object object = gson.fromJson(resultStr, callback.mType);
                        callback.onSuccess(object);
                    }
                } else {
                    callback.onError();
                }
            }
        });
    }

    private Request buildRequest(String url, Map<String, String> datas, HttpUrlType type) {
        Request.Builder request = new Request.Builder();
        request.url(url);
        if (type == HttpUrlType.GET) {
            request.get();
        } else if (type == HttpUrlType.POST) {
            // 构建body
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : datas.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
            request.post(builder.build());
        }
        return request.build();
    }


    enum HttpUrlType{
        GET,
        POST
    }
}
