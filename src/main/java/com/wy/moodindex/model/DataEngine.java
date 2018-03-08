package com.wy.moodindex.model;

import com.wy.moodindex.dao.StockMapper;
import com.wy.moodindex.model.Bean.AuthResult;
import com.wy.moodindex.model.source.PostGrabber;
import com.wy.moodindex.model.source.IGrab;
import com.wy.moodindex.model.source.OAuthController;
import com.wy.moodindex.pojo.Stock;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 从雪球抓取数据、提取数据、保存数据到数据库
 */
public class DataEngine {
    private static DataEngine instance = new DataEngine();

    public static DataEngine getInstance() {
        return instance;
    }
    private DataEngine() {
    }

    @Autowired
    private Stock stock;
    @Autowired
    private StockMapper stockMapper;

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
        stock = stockMapper.selectByPrimaryKey("SH600016");
        System.out.println(stock.getName());
        //grabController.grabData("SH600016",1);

    }

    public void stop() {

    }
}
