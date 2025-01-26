package org.example.Deque;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

/**
 * <p>环形数组实现的队列</p>
 * <ul>
 *  <li>tail停下来的位置不存储, 会浪费一个位置</li>
 *  <li>如果存储的是基本类型，那么无需考虑内存释放</li>
 *  <li>如果存储的是引用类型，应当设置该位置的引用为 null，以便内存及时释放</li>
 * </ul>
 */
public class ArrayDeque1<E> implements Deque<E> {
    private final E[] array;//初始化数组
    private int head;//头指针
    private int tail;//尾指针

    @SuppressWarnings("unchecked")
    public ArrayDeque1(int capacity) {
        array = (E[]) new Object[capacity+1];
        head = 0;
        tail=0;
    }

    /**
     * 数组长度初始化时长度比容量大1，用来存储尾指针，传入length不用减一
     * @param index 索引
     * @param length 数组长度
     * @return 索引
     */
    static int inc(int index,int length){
        if(index+1>=length){
            return 0;
        }
        return index+1;
    }

    static int dec(int index,int length){
        if(index-1<0){
            return length-1;
        }
        return index-1;
    }

    /**
     * 判断是否已满
     * @return boolean
     */
    public boolean isFull(){
        if(tail>head){
            return tail-head==array.length-1;
        }else if(tail<head){
            return head-tail==1;
        }else {
            return false;
        }
    }

    @Override
    public void addFirst(E e) {
    }

    @Override
    public void addLast(E e) {

    }

    /**
     * 头部插入
     * @param e the element to add
     * @return boolean
     */
    @Override
    public boolean offerFirst(E e) {
        if(isFull()){
            return false;
        }
        head=dec(head,array.length);
        array[head]=e;
        return true;
    }

    /**
     * 尾部插入
     * @param e the element to add
     * @return boolean
     */
    @Override
    public boolean offerLast(E e) {
        if(isFull()){
            return false;
        }
        array[tail]=e;
        tail=inc(tail,array.length);
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
     * 删除头部元素并返回头部的值
     * @return array[head]
     */
    @Override
    public E pollFirst() {
        if(isEmpty()){
            return null;
        }
        E del=array[head];
        array[head]=null;
        head=inc(head,array.length);
        return del;
    }

    /**
     * 删除尾部的节点并返回值
     * @return array[tail]
     */
    @Override
    public E pollLast() {
        if(isEmpty()){
            return null;
        }
        tail=dec(tail,array.length);
        E del=array[tail];
        array[tail]=null;
        return del;
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
     * 返回头部的值不删除
     * @return array[head]
     */
    @Override
    public E peekFirst() {
        if(isEmpty()){
            return null;
        }
        return array[head];
    }

    /**
     * 返回尾部的值不删除
     * @return array[tail]
     */
    @Override
    public E peekLast() {
        if(isEmpty()){
            return null;
        }
        return array[dec(tail,array.length)];
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
        return array.length-1;
    }

    /**
     * 是否为空
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return head==tail;
    }

    /**
     * 迭代器遍历
     * @return E cur
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index=head;

            @Override
            public boolean hasNext() {
                return index!=tail;
            }

            @Override
            public E next() {
                E cur=array[index];
                index=inc(index,array.length);
                return cur;
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
