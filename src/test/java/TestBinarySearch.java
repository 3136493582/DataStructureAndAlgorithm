import org.example.BinarySearch.BinarySearch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestBinarySearch {
    @Test
    void testBinarySearchBase() {
        int[] arr=new int[100];
        for(int i=0;i<100;i++){
            arr[i]=i;
        }
        Assertions.assertEquals(25, BinarySearch.binarySearchBase(arr,25));
        Assertions.assertEquals(-1, BinarySearch.binarySearchBase(arr,120));
        Assertions.assertEquals(50, BinarySearch.binarySearchBase(arr,50));
    }

    @Test
    void testBinarySearchPlus() {
        int[] arr=new int[100];
        for(int i=0;i<100;i++){
            arr[i]=i;
        }
        Assertions.assertEquals(25, BinarySearch.binarySearchBase(arr,25));
        Assertions.assertEquals(-1, BinarySearch.binarySearchBase(arr,120));
        Assertions.assertEquals(50, BinarySearch.binarySearchBase(arr,50));
    }
}
