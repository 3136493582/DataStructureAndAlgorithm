package org.example.Queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * 环形数组实现的队列
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E> {

    int head=0;//头指针
    int tail=0;//尾指针
    private  final E[] array;//定义空数组
    private final int length;//定义逻辑长度

    @SuppressWarnings("all")//忽略所有警告
    public ArrayQueue(int capacity) {
        length=capacity+1;//最后一个位置存放尾指针
        array= (E[]) new Object[length];
    }

    public boolean isFull(){
        return (tail+1)%length==head;
    }

    @Override
    public int size() {
        return tail;
    }

    /**
     * 是否为空
     * @return 头指针等于尾为空
     */
    @Override
    public boolean isEmpty() {
        return head==tail;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    /**
     * 迭代器遍历
     * @return 每一顶的值
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
                E e=array[index];
                index=(index+1)%length;
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
     * 向队列尾部添加
     * @param e the element to add
     * @return
     */
    @Override
    public boolean offer(E e) {
        if(isFull())
            return false;
        array[tail]=e;
        tail=(tail+1)%length;
        return true;
    }

    @Override
    public E remove() {
        return null;
    }

    /**
     * 在尾部获取值并删除
     * @return 删除的值
     */
    @Override
    public E poll() {
        if(isEmpty())
            return null;
        E e=array[head];
        head=(head+1)%length;
        return e;
    }

    @Override
    public E element() {
        return null;
    }

    /**
     * 从头部获取值，不删除
     * @return 有就返回头部值，没有返回null
     */
    @Override
    public E peek() {
        if(isEmpty())
            return null;
        return array[head];
    }

    /**
     * 循环遍历队列
     * @param c The action to be performed for each element
     */
    public void forEach(Consumer<? super E> c) {
        for(int index=head;index!=tail;index=(index+1)%length){
            c.accept(array[index]);
        }
    }
}
