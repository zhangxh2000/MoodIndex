package com.wy.moodindex.model.source;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.wy.moodindex.model.Bean.AuthResult;
import okhttp3.*;

import java.io.IOException;
import java.util.Collections;

public class OAuthController {

    public AuthResult startOAuth() {



        MediaType mediaTypeApplication  = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
        RequestBody body = RequestBody.create(mediaTypeApplication,
                "sign=b3d7dd82f1518b42521da1a8b8e85a92ab5edbd4&timestamp=1519374728463&sid=1JTY0baa96518401a7b7edda3fd2b3816b26&client_secret=txsDfr9FphRSPov5oQou74&grant_type=password&client_id=JtXbaMn7eP&type=1&version=10.2.1&");
        Request request = new Request.Builder()
                .url("https://api.xueqiu.com/provider/oauth/token?_t=1JTY0baa96518401a7b7edda3fd2b3816b26.0.0.1519378248706&_s=0e2c37")
                .header("Cookie","xq_a_token=;u=0")
                .header("Accept-Encoding","gzip")
                //.header("User-Agent","Xueqiu Android 10.2.1")
                .header("Content-Type","application/x-www-form-urlencoded;charset=UTF-8")
                .post(body)
                .build();
        Response response = null;
        try {
            response = OkHttpUtil.getHttpsClient().newCall(request).execute();
            JSONObject jsonObject = JSON.parseObject(response.body().string());
            if (jsonObject==null) {
                return null;
            }
            AuthResult result = new AuthResult();
            result.setAccessToken(jsonObject.getString("access_token"));
            result.setRefreshToken(jsonObject.getString("refresh_token"));
            result.setUid(jsonObject.getString("uid"));
            result.setCreatedTime(jsonObject.getString("uid"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
