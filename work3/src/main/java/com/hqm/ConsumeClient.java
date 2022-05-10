package com.hqm;

import java.io.IOException;

public class ConsumeClient {
    public static void main(String[] args) throws IOException {
        MqClient mqClient = new MqClient();
        String message = mqClient.consume();
        System.out.println("获取消息为："+message);
    }
}
