package org.example.SortedAlgorithm;

import java.util.Arrays;

/**
 * 单边循环（lomuto分区）要点
 *
 *  选择最右侧元素作为基准点
 *  j 找比基准点小的，i 找比基准点大的，一旦找到，二者进行交换
 *    交换时机：j 找到小的，且与 i 不相等
 *    i 找到 >= 基准点元素后，不应自增
 *  最后基准点与 i 交换，i 即为基准点最终索引
 */
public class QuickSortLomuto {

    public static void sort(int[] arr) {
        quick(arr,0,arr.length-1);
    }

    private static void quick(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int p=partition(arr,left,right);
        quick(arr,left,p-1);
        quick(arr,p+1,right);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];//基准点元素值
        int i = 0;
        int j = 0;
        while(j<right){
            if(arr[j]<pivot){//j找到比基准点小的时
                if(j!=i){//与i不相等
                    swap(arr,i,j);//与i元素交换
                }
                i++;//i指针向前+1
            }
            j++;
        }
        swap(arr,i,right);//最后基准点与 i 交换，i 即为基准点最终索引
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 656, 12, 787, 124, 878, 47, 54, 65,};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
