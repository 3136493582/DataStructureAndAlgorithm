package org.example.Queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 *环形数组实现的队列
 * @param <E>
 */
public class ArrayQueue3<E> implements Queue<E> {

    private int head = 0;//头指针
    private int tail = 0;//尾指针
    private int size = 0;//尺寸
    private final E[] array;
    private final int capacity;//容量

    /*如果传入的容量是2的阶乘，取模的值等于位与后容量的总位数-1位，使用位运算可以预防容易溢出*/
    @SuppressWarnings("all")
    public ArrayQueue3(int capacity) {
        this.capacity=(int)(Math.log10(capacity-1)/Math.log10(2))+1;//将传入的值变成2的阶乘，不足的向上进行补足
        /*capacity-=1;//将传入的值变成2的阶乘，不足的向上进行补足
        capacity|=capacity>>1;
        capacity|=capacity>>2;
        capacity|=capacity>>4;
        capacity|=capacity>>8;
        capacity|=capacity>>16;
        capacity+=1;*/
        array = (E[])new Object[capacity];
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
     * @return 空返回true，
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 是否已满
     * @return 已满返回true
     */
    public boolean isFull(){
        return size==capacity;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    /**
     * 迭代器遍历，返回每项的值
     * @return 每项的值
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int index = head&capacity;
            @Override
            public boolean hasNext() {
                return index<size;
            }

            @Override
            public E next() {
                E e=array[index];
                index++;
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
     *向队列尾添加值
     * @param e the element to add
     * @return 添加成功返回true,添加失败返回false
     */
    @Override
    public boolean offer(E e) {
        if(isFull())
            return false;
        array[tail&capacity] = e;
        tail++;
        size++;
        return true;
    }

    @Override
    public E remove() {
        return null;
    }

    /**
     * 获取队列头部值并删除
     * @return 删除成功返回E e,删除失败返回null
     */
    @Override
    public E poll() {
        if(isEmpty())
            return null;
        E e = array[head&capacity];
        array[head&capacity]=null;
        head++;
        return e;
    }


    @Override
    public E element() {
        return null;
    }

    /**
     * 获取头部的值
     * @return 头部的值
     */
    @Override
    public E peek() {
        return array[head&capacity];
    }
}
