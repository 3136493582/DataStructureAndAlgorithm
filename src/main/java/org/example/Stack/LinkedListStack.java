package org.example.Stack;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 链表实现的队列
 * @param <E>
 */
public class LinkedListStack<E> implements Deque<E> {

    private int size = 0;//尺寸
    private final int capacity;//容量
    private final Node<E> head;//头指针

    public int getCapacity() {
        return capacity;
    }

    /**
     * 构造方法
     * @param capacity
     */
    public LinkedListStack(int capacity) {
        this.capacity=capacity;
        head=new Node<>(null,null);
    }

    private class Node<E>{
        E value;//值
        Node<E> next;//下一个指针

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 函数式接口遍历
     * @param consumer
     */
    public void loop(Consumer<E> consumer){
        int i=0;
        for(Node current=head.next;i<size;i++,current=current.next){
            consumer.accept((E) current.value);
        }
    }

    @Override
    public void addFirst(E e) {

    }

    @Override
    public void addLast(E e) {

    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    /**
     * 返回栈元素不弹出
     * @return 返回栈顶元素
     */
    @Override
    public E peek() {
        return head.next.value;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    /**
     * 向栈顶压入数据
     * @param e the element to push
     */
    @Override
    public void push(E e) {
        if(isFull()){
            throw new StackOverflowError();
        }
        Node<E> newNode=new Node<>(e,head.next);
        head.next=newNode;
        size++;
    }

    /**
     * 从栈顶弹出元素
     * @return 弹出返回值，未弹出返回null
     */
    @Override
    public E pop() {
        if(isEmpty()){
            return null;
        }
        Node<E> topNode=head.next;
        E e=topNode.value;
        head.next=topNode.next;
        topNode.next=null;
        size--;
        return e;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    /**
     * 返回尺寸
     * @return size
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * 是否为空
     * @return 空返回true
     */
    @Override
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * 是否已满
     * @return 已满返回true
     */
    public boolean isFull(){
        return size==capacity;
    }

    /**
     * 迭代器遍历
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node<E> current=head.next;

            @Override
            public boolean hasNext() {
                return current!=null;
            }

            @Override
            public E next() {
                E e=current.value;
                current=current.next;
                return e;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }
}
