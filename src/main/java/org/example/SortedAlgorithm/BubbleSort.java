package org.example.SortedAlgorithm;

import java.util.Arrays;

/**
 * 每轮冒泡不断地比较相邻的两个元素，如果它们是逆序的，则交换它们的位置<br>
 * 下一轮冒泡，可以调整未排序的右边界，减少不必要比较
 */
public class BubbleSort {
    private static void bubble(int[] arr) {
        int j = arr.length - 1;
        do {
            for (int i = 0; i < j; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
            j--;
        } while (j != 0);
    }

    //递归
    private static void bubbleRecursion(int[] arr,int j) {
        for (int i = 0; i < j; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        if (j == 0) {
            return;
        }
        bubbleRecursion(arr, j - 1);
    }

    public static void main(String[] args) {
        int[] arr = {4, 656, 12, 787, 124, 878, 47, 54, 65,};
        System.out.println(Arrays.toString(arr));
        bubble(arr);
//        bubbleRecursion(arr,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
