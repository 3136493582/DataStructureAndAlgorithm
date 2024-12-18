import org.example.LinkedList.DoublyLinkedListSentinel;
import org.junit.jupiter.api.Test;

/**
 * 带哨兵的双向链表测试类
 */
public class TestDoublyLinkedListSentinel {
    DoublyLinkedListSentinel doublyLinkedListSentinel=new DoublyLinkedListSentinel();

    @Test
    public void testAddAndInsert(){
        doublyLinkedListSentinel.addFirst(1);
        doublyLinkedListSentinel.addFirst(2);
        doublyLinkedListSentinel.addFirst(4);
        doublyLinkedListSentinel.addFirst(5);
        doublyLinkedListSentinel.addFirst(6);

        doublyLinkedListSentinel.loop(System.out::println);
        System.out.println("---------------------------------");
        doublyLinkedListSentinel.insert(2,3);
        for(Integer temp:doublyLinkedListSentinel){
            System.out.println(temp);
        }
    }

    @Test
    public void testRemove(){
        doublyLinkedListSentinel.addFirst(1);
        doublyLinkedListSentinel.addFirst(2);
        doublyLinkedListSentinel.addFirst(3);
        doublyLinkedListSentinel.addFirst(4);
        doublyLinkedListSentinel.addFirst(5);

        doublyLinkedListSentinel.loop(System.out::println);
        System.out.println("---------------------------------");
        doublyLinkedListSentinel.addLast(6);
        doublyLinkedListSentinel.loop(System.out::println);
        System.out.println("---------------------------------");
        doublyLinkedListSentinel.remove(2);
        doublyLinkedListSentinel.loop(System.out::println);
        System.out.println("---------------------------------");
        doublyLinkedListSentinel.removeLast();
        doublyLinkedListSentinel.loop(System.out::println);
    }
}
