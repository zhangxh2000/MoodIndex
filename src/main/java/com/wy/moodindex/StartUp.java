package com.wy.moodindex;

import com.wy.moodindex.model.Bean.AuthResult;
import com.wy.moodindex.model.source.OAuthController;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class StartUp implements ApplicationListener<ContextRefreshedEvent> {

    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("-----onApplicationEvent---");



    }
}
