package org.example.SortedAlgorithm;

import java.util.Arrays;

/**
 * 简单的说，就是分组实现插入，每组元素间隙称为 gap
 * 每轮排序后 gap 逐渐变小，直至 gap 为 1 完成排序
 * 对插入排序的优化，让元素更快速地交换到最终位置
 */
public class ShellSort {
    public static void shellSort(int[] arr) {
        for (int gap = arr.length >>1; gap > 0; gap =gap>>1) {
            for(int low=gap; low<arr.length; low++) {
                //将low位置的元素插入至[0,low-1]的已排序
                 int t=arr[low];
                 int i=low-gap;//已排序区域指针

                while(i>=0 && arr[i]>t) {//没有找到插入位置
                    arr[i+gap] = arr[i];//突出插入位置
                    i=i-gap;
                }

                //找到插入位置
                if(i!=low-gap){
                    arr[i+gap] = t;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 656, 12, 787, 124, 878, 47, 54, 65,};
        System.out.println(Arrays.toString(arr));
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
