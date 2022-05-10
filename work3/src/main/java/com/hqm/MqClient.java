package com.hqm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MqClient {
    public void produce(String message) throws IOException {
        try (Socket socket = new Socket(InetAddress.getLocalHost(),BrokerServer.SERVER_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream())){
            out.println(message);
            out.flush();
        }
    }

    public String consume() throws IOException {
        try (Socket socket = new Socket(InetAddress.getLocalHost(),BrokerServer.SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream())){
            out.println("consume");
            out.flush();
            return in.readLine();
        }
    }
}
