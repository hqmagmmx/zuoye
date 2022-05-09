import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BrokerServer implements Runnable{
    public static int SERVER_PORT = 9999;
    private final Socket socket;

    public BrokerServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(
                BufferedReader in = new BufferedReader(new InputStreamReader((socket.getInputStream())));
                PrintWriter out = new PrintWriter(socket.getOutputStream())
            ){
            while (true){
                String str = in.readLine();
                if (str == null){
                    continue;
                }
                System.out.println("*****************************************");
                System.out.println("接收到数据："+str);
                if(str.equals("CONSUME")){//表示使用消费者消费消息
                    String message = Broker.consume();
                    out.println(message);
                    out.flush();
                }else {//剩下的情况则是使用生产者存放消息
                    Broker.produce(str);
                }
            }

            } catch (Exception e){
                e.printStackTrace();
            }

    }

    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(SERVER_PORT);
        while (true){
            BrokerServer brokerServer = new BrokerServer(server.accept());
            new Thread(brokerServer).start();
        }
    }
}
