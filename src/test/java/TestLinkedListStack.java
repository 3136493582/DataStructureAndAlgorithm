import org.example.Stack.LinkedListStack;
import org.junit.jupiter.api.Test;

public class TestLinkedListStack {

    @Test
    public void testLinkedListStack() {
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>(5);
        linkedListStack.push(1);
        linkedListStack.push(2);
        linkedListStack.push(3);
        linkedListStack.push(4);
        linkedListStack.push(5);
        linkedListStack.loop(System.out::println);
        System.out.println("--------------------------------------------");
//        Integer pop = linkedListStack.pop();
//        System.out.println(pop);
        System.out.println("--------------------------------------------");

        for(Integer i : linkedListStack) {
            System.out.println(i);
        }
        System.out.println("--------------------------------------------");
        System.out.println(linkedListStack.size());
        System.out.println(linkedListStack.getCapacity());
        System.out.println(linkedListStack.isEmpty());
        System.out.println(linkedListStack.isFull());
        System.out.println("--------------------------------------------");

        linkedListStack.push(6);
    }
}
