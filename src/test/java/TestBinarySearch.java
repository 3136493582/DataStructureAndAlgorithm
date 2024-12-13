import org.example.BinarySearch.BinarySearch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 二分查找测试类
 */
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
        Assertions.assertEquals(25, BinarySearch.binarySearchPlus(arr,25));
        Assertions.assertEquals(-1, BinarySearch.binarySearchPlus(arr,120));
        Assertions.assertEquals(50, BinarySearch.binarySearchPlus(arr,50));
    }

    @Test
    void testBinarySearchBalance(){
        int[] arr=new int[]{-1,5,8,9,15,25,45,77};
        Assertions.assertEquals(5, BinarySearch.binarySearchBalance(arr,25));
        Assertions.assertEquals(0, BinarySearch.binarySearchBalance(arr,-1));
        Assertions.assertEquals(6, BinarySearch.binarySearchBalance(arr,45));
    }

    @Test
    void testBinarySearchJava(){
        int[] arr=new int[]{-1,5,8,9,15,25,45,77};
        Assertions.assertEquals(5, BinarySearch.binarySearchJava(arr,2,8,25));
        Assertions.assertEquals(7, BinarySearch.binarySearchJava(arr,2,8,77));
        Assertions.assertEquals(-7, BinarySearch.binarySearchJava(arr,2,8,30));
        Assertions.assertEquals(1, BinarySearch.binarySearchJava(arr,0,8,5));
    }

    @Test
    void testBinarySearchLeftMost(){
        int[] arr=new int[]{-1,5,8,8,8,8,9,9,9,9,15,15,15,15,25,45,77};
        Assertions.assertEquals(2, BinarySearch.binarySearchLeftMost(arr,8));
        Assertions.assertEquals(10, BinarySearch.binarySearchLeftMost(arr,15));
        Assertions.assertEquals(-1, BinarySearch.binarySearchLeftMost(arr,40));
    }

    @Test
    void testBinarySearchRightMost(){
        int[] arr=new int[]{-1,5,8,8,8,8,9,9,9,9,15,15,15,15,25,45,77};
        Assertions.assertEquals(5, BinarySearch.binarySearchRightMost(arr,8));
        Assertions.assertEquals(13, BinarySearch.binarySearchRightMost(arr,15));
        Assertions.assertEquals(-1, BinarySearch.binarySearchRightMost(arr,40));
    }

    @Test
    void testBinarySearchLeftMostPlus(){
        int[] arr=new int[]{-1,5,8,8,8,8,9,9,9,9,15,15,15,15,25,45,77};
        Assertions.assertEquals(2, BinarySearch.binarySearchLeftMostPlus(arr,8));
        Assertions.assertEquals(10, BinarySearch.binarySearchLeftMostPlus(arr,15));
        Assertions.assertEquals(15, BinarySearch.binarySearchLeftMostPlus(arr,40));
    }

    @Test
    void testBinarySearchRightMostPlus(){
        int[] arr=new int[]{-1,5,8,8,8,8,9,9,9,9,15,15,15,15,25,45,77};
        Assertions.assertEquals(5, BinarySearch.binarySearchRightMostPlus(arr,8));
        Assertions.assertEquals(13, BinarySearch.binarySearchRightMostPlus(arr,15));
        Assertions.assertEquals(2, BinarySearch.binarySearchRightMostPlus(arr,7));//（返回插入地址有偏差）
    }
}
