package com.rocketmq.batch;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.ArrayList;
import java.util.List;

public class BatchProducer {
        public static void main(String[] args) throws Exception {
            // 实例化消息生产者Producer
            DefaultMQProducer producer = new DefaultMQProducer("group1");
            // 设置NameServer的地址
            producer.setNamesrvAddr("106.14.2.252:9876");
            //设置超时时间
            producer.setSendMsgTimeout(1000000);
            // 启动Producer实例
            producer.start();
            String topic = "BatchTest";
            List<Message> messages = new ArrayList<>();
            messages.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
            messages.add(new Message(topic, "TagA", "OrderID003", "Hello world 2".getBytes()));
            //把大的消息分裂成若干个小的消息
            ListSplitter splitter = new ListSplitter(messages);
            while (splitter.hasNext()) {
                try {
                    List<Message>  listItem = splitter.next();
                    producer.send(listItem);
                } catch (Exception e) {
                    e.printStackTrace();
                    //处理error
                }
            }
            // 如果不再发送消息，关闭Producer实例。
            producer.shutdown();
        }
}
