package com.wy.moodindex.model.process;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wy.moodindex.pojo.PostDaily;
import org.apache.commons.lang.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
}
