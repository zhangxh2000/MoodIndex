package com.wy.moodindex.model;

import com.wy.moodindex.dao.StockMapper;
import com.wy.moodindex.model.Bean.AuthResult;
import com.wy.moodindex.model.process.CountParserResult;
import com.wy.moodindex.model.process.Parser;
import com.wy.moodindex.model.process.ParserContext;
import com.wy.moodindex.model.process.PostParser;
import com.wy.moodindex.model.source.PostGrabber;
import com.wy.moodindex.model.source.IGrab;
import com.wy.moodindex.model.source.OAuthController;
import com.wy.moodindex.pojo.Stock;
import org.apache.commons.lang.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

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

    public void start() {
        if (authResult == null) {
            return;
        }
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        //选日期，生成context
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        ParserContext context = new ParserContext(calendar);
        //选一只
        Stock stock = nextStock();
        while (true) {
            do {
                int pageIndex = 1;
                //取一页数据
                IGrab grabController = new PostGrabber(authResult);
                Parser postParser = new Parser();
                do {
                    String content = grabController.grabData(stock.getStockId(), pageIndex);
                    postParser.parserPageContent(content, context);
                    pageIndex++;
                    try {
                        //sleep because the website may forbidden periodic request
                        Random random = new Random();
                        Thread.sleep(5000 + random.nextInt(8000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (!context.isHit());
                LOGGER.info(stock.getStockName() + " count current " + context.getCount() + " on " + context.getCalendar().getTime());
                if (!DateUtils.isSameDay(context.getCalendar(),Calendar.getInstance(TimeZone.getDefault()))) {
                    //日期不一样，说明己经进入新的一天，break，（如果是取历史某一天的数据，则此处逻辑有误，暂不考虑取历史数据）
                    LOGGER.info(stock.getStockName() + "break to new day");
                    context.setFinished();
                    break;
                }
            } while (!context.isFinished());
            LOGGER.info(stock.getStockName() + " count final " + context.getCount() + " on " + context.getCalendar().getTime());
            calendar.add(Calendar.DATE, 1);// 日期+1
            context = new ParserContext(calendar);
        }
    }

    private Stock nextStock() {
        //return stockMapper.selectByStockID("SH600016");//民生银行
        //return stockMapper.selectByStockID("SZ002069");
        return stockMapper.selectByStockID("SH600875");
    }


    public void genarateHistoryData() {
        if (authResult == null) {
            return;
        }
        IGrab grabController = new PostGrabber(authResult);

        int pageIndex = 0;
        PostParser.ParserResult result;
        Date date = new Date();
        Stock stock = stockMapper.selectByStockID("SH000001");
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
                    String temp = grabController.grabData(stock.getStockId(), pageIndex);
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
            LOGGER.info(stock.getStockName() + " count is " + count + " on " + date);
        }

    }

}
