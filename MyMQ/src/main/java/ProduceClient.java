public class ProduceClient {
    public static void main(String args[])throws Exception{
        MyClient client = new MyClient();
        client.produce("消息队列创建好了");
    }
}
