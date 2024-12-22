import org.example.Queue.ArrayQueue;
import org.junit.jupiter.api.Test;

/**
 * 环形数组实现的队列
 */
public class TestArrayQueue {
    @Test
    void testArrayQueue() {
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>(5);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        for(Integer i : queue) {
            System.out.println(i);
        }
        System.out.println("----------------------");
        System.out.println(queue.peek());
        System.out.println("----------------------");
        queue.poll();
        queue.poll();
        for(Integer i : queue) {
            System.out.println(i);
        }
        System.out.println("----------------------");
        queue.forEach(System.out::println);
    }
}
