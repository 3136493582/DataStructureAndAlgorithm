package org.example.SortedAlgorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * 小数据量且有序度高时，插入排序效果高
 * 大数据量用归并效果好
 * 可以结合二者
 */
public class MergeInsertionSort {
    public static void insertion(int[] a, int left, int right) {
        for (int low = left + 1; low <= right; low++) {
            int t = a[low];
            int i = low - 1;
            while (i >= left && t < a[i]) {
                a[i + 1] = a[i];
                i--;
            }
            if (i != low - 1) {
                a[i + 1] = t;
            }
        }
    }

    /*
        a1 原始数组
        i~iEnd 第一个有序范围
        j~jEnd 第二个有序范围
        a2 临时数组
     */
    public static void merge(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2) {
        int k = i;
        while (i <= iEnd && j <= jEnd) {
            if (a1[i] < a1[j]) {
                a2[k] = a1[i];
                i++;
            } else {
                a2[k] = a1[j];
                j++;
            }
            k++;
        }
        if (i > iEnd) {
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
        }
        if (j > jEnd) {
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
        }
    }

    public static void sort(int[] a1) {
        int[] a2 = new int[a1.length];
        split(a1, 0, a1.length - 1, a2);
    }

    private static void split(int[] a1, int left, int right, int[] a2) {
//        int[] array = Arrays.copyOfRange(a1, left, right + 1);
//        System.out.println(Arrays.toString(array));

        // 2. 治
        if (right == left) {
            return;
        }
        if (right - left <= 32) {
            insertion(a1, left, right);
//            System.out.println("insert..." + left + " " + right +" "+Arrays.toString(a1));
            return;
        }
        // 1. 分
        int m = (left + right) >>> 1;
        split(a1, left, m, a2);                 // left = 0 m = 0  9
        split(a1, m + 1, right, a2);       // m+1 = 1 right = 1  3
//        System.out.println(left + " " + right + " "+Arrays.toString(a1));
        // 3. 合
        merge(a1, left, m, m + 1, right, a2);
        System.arraycopy(a2, left, a1, left, right - left + 1);
    }

    public static void main(String[] args) {
//        int[] arr = {4, 656, 12, 787, 124, 878, 47, 54, 65,};
        int[] arr=new int[100];
        for(int i=0;i<arr.length;i++) {
            arr[i]= new Random().nextInt(100);
        }
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
