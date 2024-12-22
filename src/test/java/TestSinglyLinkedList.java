import org.example.LinkedList.SinglyLinkedList;
import org.junit.jupiter.api.Test;

/**
 * 单向链表测试类
 */
public class TestSinglyLinkedList {

    @Test
    public void testAddAndLoop(){
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.addFirst(1);
        singlyLinkedList.addFirst(2);
        singlyLinkedList.addFirst(3);
        singlyLinkedList.addFirst(5);
        singlyLinkedList.addFirst(6);

//        SinglyLinkedList singlyLinkedList1 = new SinglyLinkedList();

//        singlyLinkedList.loop(p->System.out.println(p));
//        singlyLinkedList.loop(System.out::println);

//        singlyLinkedList.loop2(System.out::println);

        for(Integer head : singlyLinkedList){//迭代器遍历
            System.out.println(head);
        }

//        singlyLinkedList.addLast(0);
//        singlyLinkedList.loop(System.out::println);

//        Integer nodeValue = singlyLinkedList.getNodeValue(4);
//        System.out.println(nodeValue);
//        singlyLinkedList.getNodeValue(9);

//        singlyLinkedList.insert(2,4);
//        singlyLinkedList.insert(8,4);
//        singlyLinkedList1.insert(0,2);
//        singlyLinkedList.loop(System.out::println);
//        singlyLinkedList1.loop(System.out::println);

//        singlyLinkedList.loop(System.out::println);
//        singlyLinkedList.removeFirst();
//        singlyLinkedList.remove(0);
//        System.out.println("---------------------------------");
//        singlyLinkedList.loop(System.out::println);


    }

}
