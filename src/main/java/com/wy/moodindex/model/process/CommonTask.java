package com.wy.moodindex.model.process;

import com.wy.moodindex.model.source.IGrab;

import java.util.List;

public class CommonTask implements Runnable {
    private IGrab grabber;
    private IProcess processer;
    private String stockID;
    private boolean isCoveredToday = false;
    private List<String> postList;
    public void run() {
        int pageIndex = 1;
        String postContent;
        do {
            postContent = grabber.grabData(stockID,pageIndex);
            processer.process(postContent);
            pageIndex++;
        } while (processer.isAnyMore()); //循环抓取，直到没有数据

    }
}
