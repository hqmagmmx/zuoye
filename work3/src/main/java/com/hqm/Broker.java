package com.hqm;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

// 消息处理中心类(Broker)
public class Broker {

    private final static int  MAX_SIZE = 5;
    private static final BlockingDeque<String> messageQueue = new LinkedBlockingDeque<>(MAX_SIZE);

    public static void produce(String message){
        if(messageQueue.offer(message)){
            System.out.println("向Broker投递消息成功："+message+",当前暂存消息数量："+messageQueue.size());
        }else{
            System.out.println("Broker堆积消息已满");
        }
    }
    public static String consume(){
        String message = messageQueue.poll();
        if(message != null){
            System.out.println("已消费消息：" + message + ",当前暂存消息数量："+messageQueue.size());
        }else {
            System.out.println("Broker中已无消息");
        }
        return message;
    }
}
