package org.example.LinkedList;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 意向链表
 */
public class SinglyLinkedList implements Iterable<Integer> {
    private Node head=null;//头部指针


    /**
     * 节点类
     * <p>Node 定义为内部类，是为了对外**隐藏**实现细节，没必要让类的使用者关心 Node 结构</p>
     * <p>定义为 static 内部类，是因为 Node **不需要**与 SinglyLinkedList 实例相关，多个 SinglyLinkedList实例能共用 Node 类定义</p>
     */
    private static class Node{//节点类

        int value;//值
        Node next;//下一个节点

        public Node(int value, Node next){
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 单向链表添加头节点
     * @param value 要添加的值
     */
    public void addFirst(int value){
        //头节点为空
//        head=new Node(value,null);
        //头节点不为空
        head=new Node(value,head);
    }

    /**
     * while循环函数式接口
     * @param consumer 函数式接口对象
     */
    public void loop(Consumer<Integer> consumer){
        Node temp=head;//获取头指针
        while(temp!=null){
//            System.out.println(temp.value);//打印值
            consumer.accept(temp.value);//使用函数式接口接收参数
            temp=temp.next;//将当前节点赋值为下一节点
        }
    }

    /**
     * for循环函数式接口
     * @param consumer 函数式接口对象
     */
    public void loop2(Consumer<Integer> consumer){
        for(Node temp=head;temp!=null;temp=temp.next){
            consumer.accept(temp.value);
        }
    }

    /**
     * 迭代器遍历
     * @return 返回被遍历的每项的值
     */
    @Override
    public Iterator<Integer> iterator() {
        return new NodeIterator();
    }

    /**
     * 迭代器的类
     */
    private class NodeIterator implements Iterator<Integer> {

        private Node temp=head;

        @Override
        public boolean hasNext() {//hasNext 用来判断是否还有必要调用 next
            return temp!=null;
        }

        @Override
        public Integer next() {//next 做两件事 ,返回当前节点的 value ,指向下一个节点
            Integer value=temp.value;
            temp=temp.next;//将当前节点赋值为下一节点
            return value;
        }
    }

    /**
     * 遍历链表找到最后一个节点
     * @return 最后一个节点
     */
    private Node findLast(){
        Node temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        return temp;
    }

    /**
     * 在链表末尾添加节点
     * @param value 要添加的值
     */
    public void addLast(int value){
        Node last=findLast();
        if(last==null){
            addFirst(value);
            return;
        }
        last.next=new Node(value,null);
    }

    /**
     * 根据索引查找节点
     * @param index 索引
     * @return 节点
     */
    private Node findNode(int index){
        int insc=0;//索引值
        for(Node temp=head;temp!=null;temp=temp.next,insc++){
            if(insc==index){
                return temp;
            }
        }
        return null;//没找到
    }

    /**
     * 根据索引获取节点值
     * @param index 索引
     * @return 节点
     */
    public Integer getNodeValue(int index){
        Node node = findNode(index);
        if(node==null){
            throw illgalIndex(index);
        }
        return node.value;
    }

    /**
     * 根据索引插入节点
     * @param index 索引
     * @param value 要添加的值
     */
    public void insert(int index,int value){
        if(index==0){
            addFirst(value);
            return;
        }
        Node preNode = findNode(index - 1);
        if(preNode==null){//没找到
            throw illgalIndex(index);
        }
        preNode.next=new Node(value,preNode.next);//将插入索引位置的上一个节点引用插入节点，当前节点引入上一个节点的next节点
    }

    /**
     * 抛出不合法的索引异常
     * @param index 索引
     * @return 不合法的索引提示信息
     */
    private static IllegalArgumentException illgalIndex(int index) {
        return new IllegalArgumentException(String.format("输入的索引[%d]不合法", index));
    }

    public void removeFirst(){
        if(head==null){
            throw illgalIndex(0);
        }
        head=head.next;
    }

    /**
     * 根据索引删除链表
     * @param index 索引
     */
    public void remove(int index){
        if(index==0){
            removeFirst();
        }
        Node preNode = findNode(index - 1);//上一个节点
        if(preNode==null){
            throw illgalIndex(index);
        }
        Node removedNode = preNode.next;//被删除的节点
        if(removedNode==null){
            throw illgalIndex(index);
        }
        preNode.next=removedNode.next;
    }
}
