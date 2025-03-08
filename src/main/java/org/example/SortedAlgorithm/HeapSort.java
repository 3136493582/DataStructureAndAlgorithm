package org.example.SortedAlgorithm;

import java.util.Arrays;

/**
 * 建堆排序<br>
 * 建立大顶堆<br>
 * 每次将堆顶元素（最大值）交换到末尾，调整堆顶元素，让它重新符合大顶堆特性
 */
public class HeapSort {
    public static void heapifySort(int[] arr) {
        heapify(arr, arr.length);
        for (int right = arr.length - 1; right> 0; right--) {
            swap(arr, 0, right);
            down(arr, 0, right);
        }
    }

    //建堆方法
    private static void heapify(int[] arr, int size) {
        for (int parent = size/2-1; parent >=0; parent--) {
            down(arr,parent,size);
        }
    }

    //下潜方法
    private static void down(int[] arr, int parent, int size) {
        while(true){
            int left = 2*parent+1;
            int right = 2*parent+2;
            int max=parent;
            if(left<size&&arr[left]>arr[max]){
                max=left;
            }
            if(right<size&&arr[right]>arr[max]){
                max=right;
            }
            if(max==parent){
                break;
            }
            swap(arr,parent,max);
            parent = max;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 656, 12, 787, 124, 878, 47, 54, 65,};
        System.out.println(Arrays.toString(arr));
        heapifySort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
