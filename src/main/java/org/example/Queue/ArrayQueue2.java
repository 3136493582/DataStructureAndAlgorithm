package org.example.Queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * 环形数组实现的队列,没考虑容易溢出甚至Long也存不下
 */
public class ArrayQueue2<E> implements Queue<E>{

    private int head = 0;//头指针
    private int tail = 0;//尾指针
    private int size = 0;//逻辑大小
    private final int capacity;//容量
    private final E[] array;

    @SuppressWarnings("all")
    public ArrayQueue2(int capacity) {
        this.capacity=capacity;
        array = (E[]) new Object[capacity];
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    public boolean isFull(){
        return size==capacity;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    /**
     * 迭代器遍历
     * @return 每一项的值
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int index = head;
            int loop = 0;

            @Override
            public boolean hasNext() {
                return loop<size;
            }

            @Override
            public E next() {
                E e = array[index];
                loop++;
                index = (index + 1) % capacity;
                return e;
            }
        };
    }

    /**
     * 函数式接口遍历
     * @param consumer The action to be performed for each element
     */
    public void forEach(Consumer<? super E> consumer){
        int loop = 0;
        for (int index=head; loop<size; index=(index+1)%capacity,loop++){
            consumer.accept(array[index]);
        }
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

    /**
     * 在队列尾部添加元素
     * @param e the element to add
     * @return 添加成功返回true，添加失败返回false
     */
    @Override
    public boolean offer(E e) {
        if(isFull())
            return false;
        array[tail] = e;
        tail=(tail+1)%capacity;
        size++;
        return true;
    }

    @Override
    public E remove() {
        return null;
    }

    /**
     * 在头部获取并删除值
     * @return 已删除的值，空则返回null
     */
    @Override
    public E poll() {
        if(isEmpty())
            return null;
         E e=array[head];
         head=(head+1)%capacity;
         size--;
        return e;
    }

    @Override
    public E element() {
        return null;
    }

    /**
     * 获取头部的值不删除
     * @return 头部的值
     */
    @Override
    public E peek() {
        if (isEmpty())
            return null;
        return array[head];
    }
}
