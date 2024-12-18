import org.example.LinkedList.LoopLinkedListSentinel;
import org.junit.jupiter.api.Test;

/**
 * 环形链表测试类
 */
public class TestLoopLinkedList {

    LoopLinkedListSentinel loopLinkedListSentinel=new LoopLinkedListSentinel();

    @Test
    void testAddFirst(){
        loopLinkedListSentinel.addFirst(5);
        loopLinkedListSentinel.addFirst(4);
        loopLinkedListSentinel.addFirst(3);
        loopLinkedListSentinel.addFirst(2);
        loopLinkedListSentinel.addFirst(1);
        loopLinkedListSentinel.loop(System.out::println);
    }

    @Test
    void testAddLast(){
        loopLinkedListSentinel.addLast(5);
        loopLinkedListSentinel.addLast(4);
        loopLinkedListSentinel.addLast(3);
        loopLinkedListSentinel.addLast(2);
        loopLinkedListSentinel.addLast(1);
        loopLinkedListSentinel.loop(System.out::println);
    }

    @Test
    void testRemove(){
        loopLinkedListSentinel.addLast(5);
        loopLinkedListSentinel.addLast(4);
        loopLinkedListSentinel.addLast(3);
        loopLinkedListSentinel.addLast(2);
        loopLinkedListSentinel.addLast(1);
        for (Integer i : loopLinkedListSentinel) {
            System.out.println(i);
        }
        System.out.println("----------------------------------");
        loopLinkedListSentinel.removeFirst();
        for (Integer i : loopLinkedListSentinel) {
            System.out.println(i);
        }
        System.out.println("----------------------------------");
        loopLinkedListSentinel.removeLast();
        loopLinkedListSentinel.removeLast();
        for (Integer i : loopLinkedListSentinel) {
            System.out.println(i);
        }
    }

    @Test
    void testRemoveByValue(){
        loopLinkedListSentinel.addLast(5);
        loopLinkedListSentinel.addLast(4);
        loopLinkedListSentinel.addLast(3);
        loopLinkedListSentinel.addLast(2);
        loopLinkedListSentinel.addLast(1);
        loopLinkedListSentinel.loop(System.out::println);
        System.out.println("------------------------------------------");
        loopLinkedListSentinel.removeNodeByValue(5);
        for (Integer i : loopLinkedListSentinel) {
            System.out.println(i);
        }
    }
}
