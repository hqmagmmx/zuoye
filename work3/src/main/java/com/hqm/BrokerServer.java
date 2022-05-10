package com.hqm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BrokerServer implements Runnable{
    // 运行占用的端口号
    public static int SERVER_PORT = 10010;

    private final Socket socket;

    public BrokerServer(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream())){
            while (true){
                String str = in.readLine();
                if(str == null){
                    continue;
                }
                System.out.println("接收到的原始数据：" + str);
                if (str.equals("consume")) { //表示要消费一条消息
                    String message = Broker.consume();
                    out.println (message);
                    out.flush();
                } else { // 表示要投递消息
                    Broker.produce(str);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server  = new ServerSocket(SERVER_PORT);
        while (true){
            BrokerServer brokerServer = new BrokerServer(server.accept());
            new Thread(brokerServer).start();
        }
    }
}
