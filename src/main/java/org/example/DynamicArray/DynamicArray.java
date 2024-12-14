package org.example.DynamicArray;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * 动态数组
 */
public class DynamicArray implements Iterable<Integer> {
    private int size=0;//逻辑大小
    private int capacity = 8;//容量
    private int[] array = {};//懒惰初始化

    /**
     * 添加元素至数组尾部
     *
     * @param element 添加的元素
     */
    public void addLast(int element) {
//        array[size]=element;
//        size++;
        add(size, element);
    }

    /**
     * 添加元素至目标位置，添加前调用 checkAndGrow方法进行扩容
     *
     * @param index   目标位置
     * @param element 添加的元素
     */
    public void add(int index, int element) {

        checkAndGrow(index);//扩容检查

        if (index >= 0 && index <=size) {
            //从添加元素的下标位置开始复制至后一位下标位置
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = element;
            size++;
        }else if(index>size&&index<capacity){
            array[index] = element;
            size=index+1;
        }

    }

    /**
     * [0-size]获取当前下标位置的数组元素
     *
     * @param index
     * @return 当前下标元素
     */
    public int get(int index) {
        return array[index];
    }

    /**
     * [0-size]删除元素
     *
     * @param index 删除元素下标
     * @return 删除元素
     */
    public int remove(int index) {
        int removed = array[index];
        if (index < size - 1) {
            //将删除元素之后的数组元素复制到原数组的删除下标位置
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        size--;
        return removed;
    }

    /**
     * 使用函数式接口进行接收元素遍历
     *
     * @param consumer
     */
    public void foreach(Consumer<Integer> consumer) {//函数式接口Consumer接收元素
        for (int i = 0; i < size; i++) {
            consumer.accept(array[i]);//入参
        }
    }


    /**
     * 迭代器遍历
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            private int index = 0;

            @Override
            public boolean hasNext() {//下一个是否有元素
                return index < size;
            }

            @Override
            public Integer next() {//返回当前元素并将指针移动到下一个
                return array[index++];
            }
        };
    }

    /**
     * 构造stream流方便用户初始化
     *
     * @return 有效数组元素的stream流
     */
    public IntStream stream() {
        return IntStream.of(Arrays.copyOfRange(array, 0, size));
    }

    public void checkAndGrow(int index) {
        if (size == 0) {
            array = new int[capacity];//容量检查
        } else if (size >= capacity) {
            capacity += capacity >> 1;//每次不够扩容1.5倍
            int[] newArray = new int[capacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;

        }
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }
}
