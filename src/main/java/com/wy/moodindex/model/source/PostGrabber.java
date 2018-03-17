package com.wy.moodindex.model.source;

import com.wy.moodindex.model.Bean.AuthResult;
import com.wy.moodindex.utils.OkHttpUtil;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class PostGrabber implements IGrab {

    private AuthResult authResult;

    public PostGrabber(AuthResult authResult) {
        this.authResult = authResult;
    }
    /**
     * 获取股票的主贴
     * @param stockID 股票ID
     * @param pageIndex 页索引
     * @return 主贴数据
     */
    public String grabData(String stockID, int pageIndex) {
        Request request = new Request.Builder()
                .url("https://api.xueqiu.com/statuses/search.json?_t=1JTY0baa96518401a7b7edda3fd2b3816b26.1629212250.1519265900650.1519265924933&_s=54304e&symbol="+stockID+"&count=10&source=all&page="+pageIndex+"&filter_text=1&comment=0&hl=0")
                .header("Cookie","xq_a_token=" + authResult.getAccessToken()+";u=" + authResult.getUid())
                //.header("Accept-Encoding","gzip")
                //.header("User-Agent","Xueqiu Android 10.2.1")
                .header("host","api.xueqiu.com")
                .get()
                .build();
        try {
            Response response;
            response = OkHttpUtil.getHttpsClient().newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
