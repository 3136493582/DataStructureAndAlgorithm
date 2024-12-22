package org.example.Queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * 链表实现的队列
 */
public class LinkedListQueue<E> implements Queue<E> {

    private Node<E> head = new Node<>(null, null);//队列头部
    private Node<E> tail = head;//队列尾部
    private int size = 0;//逻辑大小
    private int capacity = Integer.MAX_VALUE;//容量

    public LinkedListQueue() {
        head.next = tail;
        tail.next = head;
    }

    /**
     * 节点类
     */
    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 向队列尾部添加
     *
     * @param e the element to add
     * @return
     */
    @Override
    public boolean offer(E e) {
        if (isFull()) {
            return false;
        }
        Node<E> newNode = new Node<>(e, null);
        tail.next = newNode;
        tail = newNode;
        newNode.next = head;
        size++;
        return true;
    }

    @Override
    public E remove() {
        return null;
    }

    /**
     * 在队列头部获取值并移除
     * @return 移除的值
     */
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        Node<E> firstNode = head.next;
        head.next = firstNode.next;
        if (firstNode != tail) {
            size--;
        }
        return firstNode.value;
    }

    @Override
    public E element() {
        return null;
    }

    /**
     * 在队列头获取值，不删除
     * @return 队列头部的值
     */
    @Override
    public E peek() {
        if(isEmpty())
            return null;
        Node<E> firstNode = head.next;
        return firstNode.value;
    }


    public boolean isFull() {
        if (size == capacity) {
            return true;
        }
        return false;
    }

    /**
     * 返回队列的逻辑大小
     *
     * @return size
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * 队列是否为空
     *
     * @return 空为true, 否则false
     */
    @Override
    public boolean isEmpty() {
        if (this.size() == 0)
            return true;
        return false;
    }


    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node<E> current = head.next;

            @Override
            public boolean hasNext() {
                return current!=head;
            }

            @Override
            public E next() {
                E value = current.value;
                current = current.next;
                return value;
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
    public boolean add(E e) {
        return false;
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


}
