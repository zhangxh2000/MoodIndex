package com.wy.moodindex;

import com.wy.moodindex.model.Bean.AuthResult;
import com.wy.moodindex.model.DataEngine;
import com.wy.moodindex.model.source.OAuthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Date;

public class StartUp implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private DataEngine dataEngine;

    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getId().contains("mood-index")) {
            new Thread(new Runnable() {
                public void run() {
                    dataEngine.genarateHistoryData();
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        Date date1 = new Date(1519374728463L);
        System.out.println(date1.toString());
        Date date = new Date(1519368224559L);
        System.out.println(date.toString());
        Date date2 = new Date();

        System.out.println(date2.getTime()+","+date2.toString());
    }
}
