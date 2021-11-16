package com.rocketmq.listener;

import com.rocketmq.bean.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloListener {
    @EventListener
    public void doListener(Event event){
        log.info("处理事件内容：code={},msg={}",event.getCode(),event.getMsg());
    }
}
