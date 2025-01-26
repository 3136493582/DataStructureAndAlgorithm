import org.example.Deque.ArrayDeque1;
import org.junit.jupiter.api.Test;

public class TestArrayDeque {
    @Test
    void testArrayDeque() {
        ArrayDeque1<Integer> arrayDeque1 = new ArrayDeque1<>(5);
        arrayDeque1.offerFirst(1);
        arrayDeque1.offerLast(2);
        arrayDeque1.offerLast(3);
        arrayDeque1.offerFirst(0);
        arrayDeque1.offerFirst(-1);

        System.out.println(arrayDeque1.size());
        System.out.println("------------------------------------------------------------");
        for(Integer i : arrayDeque1) {
            System.out.println(i);
        }

        System.out.println("------------------------------------------------------------");
        System.out.println(arrayDeque1.peekFirst());
        System.out.println("------------------------------------------------------------");
        System.out.println(arrayDeque1.peekLast());
        arrayDeque1.pollFirst();
        arrayDeque1.pollLast();
        System.out.println("------------------------------------------------------------");
        for(Integer i : arrayDeque1) {
            System.out.println(i);
        }

    }
}
