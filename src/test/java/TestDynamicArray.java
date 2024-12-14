import org.example.DynamicArray.DynamicArray;
import org.junit.jupiter.api.Test;

/**
 * 动态数组测试类
 */
public class TestDynamicArray {
    @Test
    void dynamicArrayTest() {
        //动态数组初始化
        DynamicArray dynamicArray=new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        dynamicArray.addLast(5);
        dynamicArray.addLast(6);
        dynamicArray.addLast(7);
        dynamicArray.addLast(8);
        dynamicArray.addLast(9);
        dynamicArray.addLast(10);
        dynamicArray.addLast(11);
        dynamicArray.addLast(12);
        dynamicArray.addLast(13);
        dynamicArray.addLast(14);


        //添加动态数组元素
 //       dynamicArray.add(2,3);
        //使用foreach方法遍历
 //       dynamicArray.foreach(element -> System.out.println(element));

        //删除动态数组元素
//        int removed = dynamicArray.remove(5);
//        System.out.println(removed);

        //使用迭代器遍历
//        for(Integer element:dynamicArray){
//            System.out.println(element);
//        }

      dynamicArray.add(17,16);


  //      System.out.println(dynamicArray.get());
        System.out.println("----------------------------");


        //使用stream流遍历
        dynamicArray.stream().forEach(System.out::println);
        System.out.println(dynamicArray.getSize());
        System.out.println(dynamicArray.getCapacity());
    }
}
