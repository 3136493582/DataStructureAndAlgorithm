import org.example.Deque.LinkedListDeque;
import org.junit.jupiter.api.Test;

public class TestLinkedDeque {
    @Test
    public void testLinkedDeque() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<>(6);
        linkedListDeque.offerFirst(1);
        linkedListDeque.offerLast(2);
        linkedListDeque.offerLast(3);
        linkedListDeque.offerLast(4);
        linkedListDeque.loop(System.out::println);
        System.out.println(linkedListDeque.isFull());
        System.out.println(linkedListDeque.isEmpty());
        System.out.println("-------------------------------------------");


        linkedListDeque.offerLast(5);
        linkedListDeque.offerFirst(-1);
        for(Integer i : linkedListDeque) {
            System.out.println(i);
        }
        System.out.println(linkedListDeque.isFull());
        System.out.println(linkedListDeque.isEmpty());

        System.out.println("-------------------------------------------");
        System.out.println(linkedListDeque.peekFirst());
        System.out.println(linkedListDeque.peekLast());

        System.out.println("-------------------------------------------");
        linkedListDeque.pollFirst();
        linkedListDeque.pollLast();
        linkedListDeque.loop(System.out::println);
    }
}
