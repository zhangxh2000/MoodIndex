package com.wy.moodindex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;

/**
 * test
 */
@Controller
@RequestMapping("/hello")
public class Hello {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "test";
    }

}