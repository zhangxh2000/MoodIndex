package com.wy.moodindex.model;

import com.wy.moodindex.model.Bean.AuthResult;
import com.wy.moodindex.model.source.PostGrabber;
import com.wy.moodindex.model.source.IGrab;
import com.wy.moodindex.model.source.OAuthController;

/**
 * 从雪球抓取数据、提取数据、保存数据到数据库
 */
public class DataEngine {
    public static DataEngine dataEngine;
    public static DataEngine getInstance() {
        return dataEngine;
    }

    /**
     * 开始工作
     */
    public void start() {
        OAuthController oAuthController = new OAuthController();
        AuthResult authResult = oAuthController.startOAuth();
        if (authResult == null) {
            System.out.println("xueqiu auth failed");
            return;
        }
        IGrab grabController = new PostGrabber(authResult);
        grabController.grabData("SH600016",1);

    }

    public void stop() {

    }
}
