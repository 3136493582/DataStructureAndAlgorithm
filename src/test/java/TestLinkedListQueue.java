import org.example.Queue.LinkedListQueue;
import org.junit.jupiter.api.Test;

/**
 * 链表实现的队列测试类
 */
public class TestLinkedListQueue {
    @Test
    void testLinkedListQueue() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        System.out.println("队列是否为空："+queue.isEmpty());
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        for(Integer i : queue) {
            System.out.println(i);
        }
        System.out.println("队列是否为空："+queue.isEmpty());
        System.out.println("---------------------");
        System.out.println("队列的尺寸："+queue.size());
        System.out.println("---------------------");
        System.out.println("队列的第一个节点值："+queue.peek());
        System.out.println("---------------------");
        System.out.println("删除队列的第一个节点："+queue.poll()+"剩下的队列节点：");
        for(Integer i : queue) {
            System.out.println(i);
        }
    }
}
