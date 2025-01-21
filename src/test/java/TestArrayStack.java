import org.example.Stack.ArrayStack;
import org.junit.jupiter.api.Test;

public class TestArrayStack {

    @Test
    public void testArrayStack() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(5);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.loop(System.out::println);
        System.out.println(arrayStack.isFull());
        System.out.println(arrayStack.isEmpty());
        System.out.println(arrayStack.size());
        System.out.println(arrayStack.getTop());
        System.out.println("-----------------------------------------------");

        arrayStack.pop();
        for(Integer i : arrayStack) {
            System.out.println(i);
        }

        System.out.println(arrayStack.peek());
    }
}
