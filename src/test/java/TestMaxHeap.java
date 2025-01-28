import org.example.Heap.MaxHeap;
import org.junit.jupiter.api.Test;


public class TestMaxHeap {
    @Test
    public void testMaxHeap(){
        int[] arr = {2,3,5,6,1,7,9,8,4};
        MaxHeap maxHeap = new MaxHeap(arr);
        System.out.println(maxHeap.toString());

        System.out.println(maxHeap.peek());

        System.out.println(maxHeap.offer(100));
        System.out.println(maxHeap.toString());

        maxHeap.poll();
        System.out.println(maxHeap.toString());

        System.out.println("------------------------------------");
        System.out.println(maxHeap.poll(3));
        System.out.println(maxHeap.toString());

        System.out.println("------------------------------------");

        maxHeap.replace(-1);
        System.out.println(maxHeap.toString());
    }
}
