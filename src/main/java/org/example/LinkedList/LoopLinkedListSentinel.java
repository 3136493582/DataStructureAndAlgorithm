package org.example.LinkedList;

import javax.print.attribute.standard.NumberOfInterveningJobs;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * 环形链表
 */
public class LoopLinkedListSentinel implements Iterable<Integer> {

    private final Node head;

    /**
     * 初始化head node哨兵节点
     */
    public LoopLinkedListSentinel() {
        this.head = new Node(null,-1,null);
        this.head.next = head;
        this.head.prev = head;
    }



    /**
     * node类
     */
    private class Node {
        Node prev;
        int value;
        Node next;

        public Node(Node prev, int value, Node next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 在链表头插入节点
     * @param value 待插入的值
     */
    public void addFirst(int value) {
        Node first=head.next;
        Node added=new Node(head,value,first);
        head.next=added;
        first.prev=head;
    }

    /**
     * 在链表末尾插入值
     * @param value 待插入的值
     */
    public void addLast(int value) {
        Node lastNode=head.prev;
        Node added=new Node(lastNode,value,head);
        lastNode.next=added;
        head.prev=added;
    }

    /**
     * 删除第一项
     */
    public void removeFirst(){
        Node first=head.next;
        head.next=first.next;
        first.prev=head;
    }

    /**
     * 删除最后一项
     */
    public void removeLast(){
         Node lastNode=head.prev;
         head.prev=lastNode.prev;
         lastNode.prev.next=head;
    }


    /**
     * 假设链表的节点值不重复
     * @param value 要删除节点的值
     * @return 要删除的节点
     */
    private Node findNodeByValue(int value){
        if(head.next==head){
            throw new NoSuchElementException();
        }
        for(Node current=head.next; current!=head; current=current.next){
            if(current.value==value){
                return current;
            }
        }
        return null;//没找到
    }

    /**
     * 根据要删除节点的值删除节点
     * @param value 要删除节点的值
     */
    public void removeNodeByValue(int value){
        Node removed=findNodeByValue(value);
        Node prev=removed.prev;
        Node next=removed.next;
        prev.next=next;
        next.prev=prev;
    }

    /**
     * 使用函数式接口遍历
     * @param consumer 消费者函数式接口
     */
    public void loop(Consumer<Integer> consumer) {
        int i=0;
        for (Node current=head.next; current!=head; current=current.next,i++) {
            consumer.accept(current.value);
        }
    }

    /**
     * 迭代器遍历
     * @return 返回每一项中存储的值
     */
    @Override
    public Iterator<Integer> iterator() {
        return new IntegerIterator();
    }

    private class IntegerIterator implements Iterator<Integer> {
        private Node current=head.next;

        @Override
        public boolean hasNext() {
            return current!=head;
        }

        @Override
        public Integer next() {
            int value=current.value;
            current=current.next;
            return value;
        }
    }
}
