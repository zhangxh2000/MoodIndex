package com.wy.moodindex.model;

import com.wy.moodindex.dao.StockMapper;
import com.wy.moodindex.model.Bean.AuthResult;
import com.wy.moodindex.model.process.PostParser;
import com.wy.moodindex.model.source.PostGrabber;
import com.wy.moodindex.model.source.IGrab;
import com.wy.moodindex.model.source.OAuthController;
import com.wy.moodindex.pojo.Stock;
import org.apache.commons.lang.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 从雪球抓取数据、提取数据、保存数据到数据库
 */
public class DataEngine {

    private static Logger LOGGER = LogManager.getLogger();
    private AuthResult authResult;
    @Autowired
    private StockMapper stockMapper;

    public void init() {
        OAuthController oAuthController = new OAuthController();
        authResult = oAuthController.startOAuth();
        if (authResult == null) {
            System.out.println("DataEngine init failed,because xueqiu auth failed");
        }
    }

    public void destroy() {

    }

    public class ParserContext {
        public long createdTime;
        public boolean isEverReached;
    }

    public void start() {
        if (authResult == null) {
            return;
        }
        Date thisDay = new Date();
        //选一只
        Stock stock = nextStock();
        int pageIndex = 0;
        //取一页数据
        IGrab grabController = new PostGrabber(authResult);
        PostParser postParser = new PostParser();
        PostParser.ParserResult result = null;
        int count = 0;
        ParserContext context = new ParserContext();
        context.isEverReached = false;
        try {
            do {
                String content = grabController.grabData(stock.getId(), pageIndex);
                if (result!=null&& result.newerCreatedTime!=0) {
                    context.createdTime = result.newerCreatedTime;
                } else {
                    context.createdTime = 0;
                }
                result = postParser.processFromDate(content,context);
                pageIndex++;
                count += result.count;
                try {
                    Random random = new Random(16000);
                    Thread.sleep(500 + random.nextInt(2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (!result.thisDayFinished);
        }catch (RuntimeException e) {
            LOGGER.warn("genarateHistoryData exception " + e);
        }


        LOGGER.info(stock.getName() + " count is " + count + " on ");
    }

    private Stock nextStock() {
        return stockMapper.selectByPrimaryKey("SH600606");
    }


    public void genarateHistoryData() {
        if (authResult == null) {
            return;
        }
        IGrab grabController = new PostGrabber(authResult);

        int pageIndex = 0;
        PostParser.ParserResult result;
        Date date = new Date();
        Stock stock = stockMapper.selectByPrimaryKey("SH000001");
        PostParser postParser = new PostParser();
        for (int i = 0; i < 100; i++) {
            int count = 0;
            date = DateUtils.addDays(date, -1);
            postParser.setDate(date);
            pageIndex--;
            if (pageIndex < 0) {
                pageIndex = 0;
            }
            try {
                do {
                    String temp = grabController.grabData(stock.getId(), pageIndex);
                    result = postParser.process(temp);
                    pageIndex++;
                    count += result.count;
                    try {
                        Random random = new Random(16000);
                        Thread.sleep(500 + random.nextInt(2000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (!result.thisDayFinished);
            } catch (RuntimeException e) {
                LOGGER.warn("genarateHistoryData exception " + e);
                break;
            }
            LOGGER.info(stock.getName() + " count is " + count + " on " + date);
        }

    }

}
