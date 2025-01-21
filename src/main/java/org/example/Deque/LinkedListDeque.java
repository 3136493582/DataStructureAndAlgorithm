package org.example.Deque;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 链表实现的双端队列
 */
public class LinkedListDeque<E> implements Deque<E> {
    private final int capacity;//容量
    private int size;//当前队列大小
    private final Node<E> sentinel=new Node<>(null,null,null);//哨兵节点

    @SuppressWarnings("all")
    public LinkedListDeque(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
    }

    static class Node<E> {
        Node<E> prev;
        E element;
        Node<E> next;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    public boolean isFull(){
        return size==capacity;
    }

    /**
     * 函数式接口遍历队列
     * @param consumer
     */
    public void loop(Consumer<E> consumer){
        for(Node<E> cur=sentinel.next;cur!=sentinel;cur=cur.next){
            consumer.accept(cur.element);
        }
    }

    @Override
    public void addFirst(E e) {

    }

    @Override
    public void addLast(E e) {

    }

    /**
     * 在队列头部添加节点
     * @param e the element to add
     * @return 成功返回true，失败返回false
     */
    @Override
    public boolean offerFirst(E e) {
        if(isFull()){
            return false;
        }
        Node<E> firstNode=sentinel.next;
        Node<E> newNode=new Node<E>(sentinel,e,sentinel.next);
        firstNode.prev=newNode;
        sentinel.next=newNode;
        size++;
        return true;
    }

    /**
     * 在队列尾部添加节点
     * @param e the element to add
     * @return 成功返回true，失败返回false
     */
    @Override
    public boolean offerLast(E e) {
        if(isFull()){
            return false;
        }
        Node<E> lastNode=sentinel.prev;
        Node<E> newNode=new Node<E>(lastNode,e,sentinel);
        lastNode.next=newNode;
        sentinel.prev=newNode;
        size++;
        return true;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    /**
     * 在头部移除节点
     * @return 成功返回节点值，失败返回null
     */
    @Override
    public E pollFirst() {
        if(isEmpty()){
            return null;
        }
        Node<E> delNode=sentinel.next;
        Node<E> firstNode=delNode.next;
        sentinel.next=firstNode;
        firstNode.prev=sentinel;
        size--;
        return delNode.element;
    }

    /**
     * 在尾部移除节点
     * @return 成功返回节点值，失败返回null
     */
    @Override
    public E pollLast() {
        if(isEmpty()){
            return null;
        }
        Node<E> delNode=sentinel.prev;
        Node<E> lastNode=delNode.prev;
        sentinel.prev=lastNode;
        lastNode.next=sentinel;
        size--;
        return delNode.element;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    /**
     * 获取首节点的值，不删除
     * @return 首节点的值
     */
    @Override
    public E peekFirst() {
        return sentinel.next.element;
    }

    /**
     * 获取尾节点的值，不删除
     * @return 尾节点的值
     */
    @Override
    public E peekLast() {
        return sentinel.prev.element;
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

    @Override
    public E peek() {
        return null;
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

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
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

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 迭代器遍历
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current=sentinel.next;
            @Override
            public boolean hasNext() {
                return current != sentinel;
            }

            @Override
            public E next() {
                E e=current.element;
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
