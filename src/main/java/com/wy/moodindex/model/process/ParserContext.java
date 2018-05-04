package com.wy.moodindex.model.process;

import java.util.Calendar;
import java.util.Date;

public class ParserContext {


    private int count;
    private Calendar targetDate;
    private long bigTime;
    private long smallTime;
    private boolean finished;
    private boolean everHitThisDay;//是否取（经历）过这一天数据

    public ParserContext(Calendar date) {
        targetDate = date;
        everHitThisDay = false;
        finished = false;
        bigTime = -1;
        Calendar calendar = (Calendar) date.clone();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        bigTime = calendar.getTimeInMillis();//最大时间初始置为最小值
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,1000);
        smallTime = calendar.getTimeInMillis();//最小时间初始置为最大值
        count = 0;

    }

    public void increaseCount() {
        count++;
    }

    public int getCount() {
        return count;
    }
    public boolean isFinished() {
        return finished;
    }

    public void setFinished() {
        finished = true;
    }

    public void setBigTime(long time) {
        bigTime = time;
    }

    public void setSmallTime(long time) {
        smallTime = time;
    }

    public long getBigTime() {
        return bigTime;
    }

    public long getSmallTime() {
        return smallTime;
    }

    public Calendar getCalendar() {
        return targetDate;
    }

    public void hit() {
        everHitThisDay = true;
    }

    public boolean isHit() {
        return everHitThisDay;
    }
}
