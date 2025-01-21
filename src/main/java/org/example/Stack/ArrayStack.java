package org.example.Stack;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 数组实现的栈，数组头部为栈底，数组尾部为栈顶
 * @param <E>
 */
public class ArrayStack<E> implements Deque<E> {

    private final E[] array;//定义数组
    private int top = -1;//数组大小判断是否越界

    @SuppressWarnings("all")
    public ArrayStack(int capacity) {
        this.array=(E[])new Object[capacity];
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public void loop(Consumer<E> consumer){
        for(int index=top;index>=0;index--){
            consumer.accept(array[index]);
        }
    }

    public boolean isFull(){
        return top == array.length;
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
     * 获取栈顶的元素，不弹出
     * @return E e
     */
    @Override
    public E peek() {
        return array[top];
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
     * 往栈顶添加元素
     * @param e the element to push
     */
    @Override
    public void push(E e) {
        if(isFull()){
            throw new StackOverflowError();
        }
        array[++top]=e;
    }

    /**
     * 弹出栈顶元素
     * @return 被弹出的元素值
     */
    @Override
    public E pop() {
        E e=array[top];
        array[top]=null;
        top--;
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

    @Override
    public int size() {
        return this.top;
    }

    @Override
    public boolean isEmpty() {
        return top==0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = top;
            @Override
            public boolean hasNext() {
                return index>=0;
            }

            @Override
            public E next() {
                E e=array[index];
                index--;
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
