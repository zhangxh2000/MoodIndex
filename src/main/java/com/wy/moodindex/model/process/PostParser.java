package com.wy.moodindex.model.process;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wy.moodindex.model.DataEngine;
import com.wy.moodindex.pojo.PostDaily;
import org.apache.commons.lang.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.Date;

public class PostParser implements IProcess {
    private static Logger LOGGER = LogManager.getLogger();
    private Date desiredDate;
    private boolean postReachedDate;

    public PostParser( ) {
    }

    public void setDate(Date date) {
        desiredDate = date;
        LOGGER.info("DATE:" + date);
    }

    public class ParserResult {
        public String stockID;
        public Date date;
        public int count;
        public boolean thisDayFinished;//是否己取完这一天的数据
        public long newerCreatedTime;
    }
    
    private boolean contentNewer;//content中内容相对于期望的时间更新
    public boolean isContentNewer() {
        return contentNewer;
    }

    public ParserResult process(String content) {
        ParserResult result = new ParserResult();
        result.count = 0;
        result.thisDayFinished = false;

        PostDaily postDaily = new PostDaily();
        JSONObject jsonObject = JSONObject.parseObject(content);
        if (jsonObject == null) {
            LOGGER.info(content);
            return result;
        }
        JSONArray postList = jsonObject.getJSONArray("list");

        for (int i=0;i<postList.size();i++) {
            JSONObject post = postList.getJSONObject(i);
            long time = post.getLongValue("created_at");
            Date postDate = new Date(time);
            if (!DateUtils.isSameDay(postDate, desiredDate)) {
               //不是同一天
                if (postDate.getTime()> desiredDate.getTime()) {
                    //post时间更新一些，继续判断下一个
                    continue;
                } else {
                    //post己经较旧了，退出
                    result.thisDayFinished = true;
                    break;
                }
            } else {
                result.count++;
            }
        }
        return result;
    }

    public boolean isAnyMore() {
        return false;
    }

    //private long lastCreatedTime;
    private boolean thisDayHasReached = false;//does this day ever reached?
    //public void lastCreatedTime(long time) {
    //    lastCreatedTime = time;
    //    thisDayHasReached = false;
    //}

    /**
     * 从某一天开始处理数据
     * @param content
     * @return
     */
    public ParserResult processFromDate(String content,DataEngine.ParserContext context) {
        ParserResult result = new ParserResult();
        result.count = 0;
        result.thisDayFinished = false;
        Date lastCreatedDate;
        PostDaily postDaily = new PostDaily();
        JSONObject jsonObject = JSONObject.parseObject(content);
        if (jsonObject == null) {
            LOGGER.info(content);
            return result;
        }
        JSONArray postList = jsonObject.getJSONArray("list");
        if (context.createdTime ==0) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);
            context.createdTime = calendar.getTimeInMillis();
            lastCreatedDate = calendar.getTime();
        } else {
            lastCreatedDate = new Date(context.createdTime);
        }
        for (int i = 0; i < postList.size(); i++) {
            JSONObject post = postList.getJSONObject(i);
            long time = post.getLongValue("created_at");
            Date postDate = new Date(time);
            if (!DateUtils.isSameDay(postDate, lastCreatedDate)) {
                //not same day
                if (time>lastCreatedTime) {
                    //newer day
                    if (thisDayHasReached) {
                        result.thisDayFinished = true;
                        break;
                    } else {
                        continue;
                    }
                } else {
                    //LOGGER.debug("this should not happen");
                    //post己经较旧了，退出
                    if (!thisDayHasReached) {
                        LOGGER.warn("this day has no post " + lastCreatedDate);
                    }
                    result.thisDayFinished = true;
                    break;
                }
            } else {
                if (time>lastCreatedTime) {
                    lastCreatedTime = time;
                    result.newerCreatedTime = time;
                }
                result.count++;
            }
        }
        return result;
    }

}
