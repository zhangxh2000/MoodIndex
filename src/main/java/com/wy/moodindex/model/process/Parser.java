package com.wy.moodindex.model.process;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Calendar;

public class Parser {
    private static Logger LOGGER = LogManager.getLogger();

    public void parserPageContent(String src,ParserContext context) {
        JSONObject jsonObject = JSONObject.parseObject(src);
        if (jsonObject == null) {
            LOGGER.info(src);
            return;
        }
        JSONArray postList = jsonObject.getJSONArray("list");
        for (int i = 0; i < postList.size(); i++) {
            JSONObject post = postList.getJSONObject(i);
            long time = post.getLongValue("created_at");
            Calendar postCalendar = (Calendar) context.getCalendar().clone();
            postCalendar.setTimeInMillis(time);
            if (!DateUtils.isSameDay(postCalendar, context.getCalendar())) {
                //不是同一天
                if (postCalendar.getTimeInMillis()> context.getCalendar().getTimeInMillis()) {
                    //post取到新的一天了，如果hit过，则认为任务结束
                    if (context.isHit()) {
                        context.setFinished();
                    }
                    continue;
                } else {
                    //己经取到前一天了，当天肯定取到过（有可能count为0）
                    //TODO 如果是取历史某一天的数据，而这一天又没有数据，则需要在此处设置finished
                    context.hit();
                    break;
                }
            } else {
                boolean needIncrease = false;
                if (time<context.getSmallTime()) {
                    context.setSmallTime(time);
                    needIncrease = true;
                }
                if (time>context.getBigTime()) {
                    context.setBigTime(time);
                    needIncrease = true;
                }
                if (needIncrease) {
                    context.increaseCount();
                } else {
                    break;
                }
            }
        }
    }
}
