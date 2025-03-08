package org.example.SortedAlgorithm;

import java.util.Arrays;

/**
 * 将数组分为两部分 [0 .. low-1]  [low .. a.length-1]
 *   左边[0 .. low-1]* *是已排序部分*
 *   右边[low .. a.length-1]* *是未排序部分*
 * 每次从未排序区域取出  low*  位置的元素, 插入到已排序区域
 */
public class InsertionSort {
    public static void sort(int[] arr) {
        for(int low=1;low<arr.length;low++) {
            //将low位置的元素插入至[0,low-1]的已排序区域
            int t = arr[low];
            int i=low-1;//已经排序区域指针

            while(i>=0 && arr[i]>t) {//没有插入位置
                arr[i+1]=arr[i];//空出插入位置
                i--;
            }

            //找到插入位置
            if(i!=low-1){
                arr[i+1]=t;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 656, 12, 787, 124, 878, 47, 54, 65,};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
