package org.example.Heap;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <h3>大顶堆(每个根节点比叶子节点大，root最大)</h3>
 * <p>leftchild=parent * 2 + 1</p>
 */
public class MaxHeap {
    private int[] array;
    private int size;

    public MaxHeap(int capacity) {
        this.array = new int[capacity];
        this.size = 0;
    }

    /**
     * 根据传入的数组进行建堆
     *
     * @param array
     */
    public MaxHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    /**
     * 获取堆顶的元素
     *
     * @return array[0]
     */
    public int peek() {
        return array[0];
    }

    /**
     * 替换堆顶元素
     *
     * @param replaced 新元素
     */
    public void replace(int replaced) {
        array[0] = replaced;
        heapify();
    }

    /**
     * 在堆中添加一个元素
     *
     * @param offered 新添加的元素
     * @return 成功true, 失败false
     */
    public boolean offer(int offered) {
        int[] newArray = new int[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);//复制数组
        this.array = newArray;
        if (isFull()) {
            return false;
        }
        up(offered, size);
        size++;
        return true;
    }

    /**
     * 删除堆顶并返回值
     *
     * @return 堆顶的值
     */
    public int poll() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int deleted = array[0];
        swap(0, size - 1);
        int[] newArray = new int[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, array.length - 1);//复制数组
        array = newArray;
        size--;
        heapify();
        return deleted;
    }

    /**
     * 删除指定索引元素
     *
     * @param index 索引
     * @return 被删除的元素值
     */
    public int poll(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int deleted = array[index];
        swap(index, size - 1);
        int[] newArray = new int[array.length - 1];
        System.arraycopy(array, 0, newArray, 0, array.length - 1);//复制数组
        array = newArray;
        size--;
        heapify();
        return deleted;
    }

    /**
     * 根据一个数组建堆
     */
    public void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {//找到最后一个非页子节点
            down(i);
        }
    }

    /**
     * 将parent索引处的元素下潜，与两个孩子较大者交换，直至没孩子或孩子没他大
     *
     * @param parent
     */
    private void down(int parent) {
        int left = parent * 2 + 1;
        int right = left + 1;
        int max = parent;
        if (left < size && array[left] > array[max]) {//静态数组有的父亲节点没有孩子，数组下标越界
            max = left;
        }
        if (right < size && array[right] > array[max]) {
            max = right;
        }
        if (max != parent) {//找到了更大的孩子
            swap(max, parent);
            down(max);
        }
    }

    /**
     * 将offered上浮，直到小于某个父元素或堆顶
     *
     * @param offered 要上浮的元素
     * @param index   起始索引指针
     */
    private void up(int offered, int index) {
        int child = index;
        while (child > 0) {
            int parent = (child - 1) / 2;
            if (offered > array[parent]) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child] = offered;
    }

    /**
     * 交换两个数组的值
     *
     * @param i
     * @param j
     */
    public void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
