package com.rocketmq.controller;

import com.rocketmq.bean.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Action;

@Controller
public class TestController {
    @Autowired
    private ApplicationContext applicationContext;
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam String name){
        Event event = new Event(200,"hello");
        applicationContext.publishEvent(event);
        return "hello" +name;
    }
}
