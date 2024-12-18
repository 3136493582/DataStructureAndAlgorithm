package org.example.LinkedList;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 带哨兵的双向链表
 */
public class DoublyLinkedListSentinel implements Iterable<Integer>{
    /**
     * 定义头节点和尾节点哨兵
     */
    private final Node head;
    private final Node tail;

    /**
     * 双向链表初始化赋值
     */
    public DoublyLinkedListSentinel(){
        head=new Node(null,-1,null);
        tail=new Node(null,-1,null);
        head.next=tail;
        tail.prev=head;
    }

    /**
     * 节点类
     */
    private static class Node{
        private Node prev;
        private int value;
        private Node next;

        public Node(Node pre,int value,Node next){
            this.prev = pre;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 添加到头节点
     * @param value 待添加的值
     */
    public void addFirst(int value){
        insert(0,value);
    }

    private Node findNode(int index){
        int i=-1;
        for (Node node=head;node!=tail;node=node.next,i++){
            if(i==index){
                return node;
            }
        }
        return null;//没找到
    }

    /**
     * 插入节点
     * @param index 插入索引
     * @param value 待插入值
     */
    public void insert(int index,int value){
        Node prevNode=findNode(index-1);
        if(prevNode==null){
            throw getIllegalArgumentException(index);
        }
        Node next=prevNode.next;
        Node addedNode=new Node(prevNode,value,next);
        prevNode.next=addedNode;
        next.prev=addedNode;
    }

    /**
     * 根据索引删除节点
     * @param index
     */
    public void remove(int index){
        Node prevNode=findNode(index-1);
        if(prevNode==null){
            throw getIllegalArgumentException(index);
        }
        Node removed=prevNode.next;
        if(removed==tail){
            throw getIllegalArgumentException(index);
        }
        prevNode.next=removed.next;
        removed.next.prev=prevNode;
    }

    /**
     * 在链表末尾添加节点
     * @param value 待添加的值
     */
    public void addLast(int value){
        Node lastNode=tail.prev;
        Node addedNode=new Node(lastNode,value,tail);
        lastNode.next=addedNode;
        tail.prev=addedNode;
    }

    /**
     * 删除最后一个节点
     */
    public void removeLast(){
        Node removed=tail.prev;
        if(removed==head){
            throw getIllegalArgumentException(0);
        }
        removed.prev.next=tail;
        tail.prev=removed.prev;
    }

    /**
     * 函数式接口循环遍历
     * @param consumer 消费者接口
     */
    public void loop(Consumer<Integer> consumer){
        for(Node node=head.next;node!=tail;node=node.next){
            consumer.accept(node.value);
        }
    }

    /**
     * 迭代器遍历
     * @return 返回满足条件每一项
     */
    @Override
    public Iterator<Integer> iterator() {
        return new IntegerIterator();
    }


    /**
     * 索引不合法抛出异常
     * @param index 插入索引
     * @return
     */
    private static IllegalArgumentException getIllegalArgumentException(int index) {
        return new IllegalArgumentException(String.format("输入的索引[%d]不合法", index));
    }

    /**
     * 迭代器类
     */
    private class IntegerIterator implements Iterator<Integer> {
        private Node temp=head.next;

        @Override
        public boolean hasNext() {
            return temp!=tail;
        }

        @Override
        public Integer next() {
            int value=temp.value;
            temp=temp.next;
            return value;
        }
    }
}
