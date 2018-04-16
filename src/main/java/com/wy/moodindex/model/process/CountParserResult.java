package com.wy.moodindex.model.process;

import org.apache.commons.lang.time.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class CountParserResult {
    private ArrayList<Calendar> dateList = new ArrayList<Calendar>();
    private HashMap<Calendar,Integer> postCountHashMap = new HashMap<Calendar, Integer>();

    /**
     * 指定日期增加指定数量的帖子
     * @param calendar 日期
     * @param count 数量
     */
    public void addResult(Calendar calendar,int count) {
        for (Calendar date : postCountHashMap.keySet()) {
            if (DateUtils.isSameDay(calendar,date)) {
                int temp = postCountHashMap.get(date);
                postCountHashMap.put(date,count+temp);
                return;
            }
        }
        dateList.add(calendar);
        postCountHashMap.put(calendar,count);
    }

    public List<Calendar> getDateList() {
        return dateList;
    }

    public int getCountOfDate(Calendar calendar) {
        for (Calendar date : postCountHashMap.keySet()) {
            if (DateUtils.isSameDay(calendar,date)) {
                return postCountHashMap.get(date);
            }
        }
        return 0;
    }
}
