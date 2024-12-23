import org.example.Queue.ArrayQueue2;
import org.junit.jupiter.api.Test;

/**
 * 环形数组实现的队列2
 */
public class TestArrayQueue2 {
    @Test
    void testArrayQueue() {
       ArrayQueue2<Integer> queue = new ArrayQueue2<>(5);
        System.out.println(queue.isEmpty());
        System.out.println("---------------------");
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        for(Integer i : queue) {
            System.out.println(i);
        }
        System.out.println("---------------------");
        System.out.println(queue.peek());
        System.out.println("---------------------");
        System.out.println(queue.size());
        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());
        System.out.println("---------------------");
        queue.poll();
        queue.forEach(System.out::println);
        System.out.println(queue.size());
        System.out.println("---------------------");

    }
}
