import java.util.concurrent.ArrayBlockingQueue;

public class Broker {
    private static final int QUEUE_MAXSIZE = 3;
    private static ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(QUEUE_MAXSIZE);

    public static void produce(String msg){
        if (messageQueue.offer(msg)) {
            System.out.println("-----------------------------------------");
            System.out.println("成功向消息队列投放一条消息："+msg+"，当前消息队列有消息数："+messageQueue.size());
        }else {
            System.out.println("-----------------------------------------");
            System.out.println("当前消息队列消息数已满，当前消息无法插入队列中，含有的消息数是："+messageQueue.size());
        }
    }

    public static String consume(){
        String message = messageQueue.poll();
        if(message != null){
            System.out.println("成功消费一条消息："+message+"，消息队列中还有的消息数"+messageQueue.size());
        }else {
            System.out.println("当前消息队列中没有任何消息");
        }
        return message;
    }
}
