package org.example.SortedAlgorithm;


import java.util.Arrays;

/**
 * 计数排序
 * 1、找到最大值，创建一个大小为最大值+1的count数组
 * 2、count数组的索引对应原始数组的元素，用来统计元素的出现次数
 * 3、遍历count数组，根据count数组的索引（即原始数组的元素）以及出现的次数，生成排序后的内容（count数组的索引是已排序好的）
 */
public class CountingSort {
    public static void sort(int[] arr) {
        //找最大值
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }else if (min > arr[i]) {
                min = arr[i];
            }
        }

        int[] count=new int[max-min+1];

        for(int v:arr){
            count[v-min]++;
        }

        int k=0;
        for (int i = 0; i < count.length; i++) {
            //i等于元素值，count(i)等于出现次数
            while(count[i]>0){
                arr[k++]=i+min;
                count[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr={0,5,5,3,6,9,7,4,5,2,8,7,9,6,4,1,-1,-2,-1,-4,-6,-5,-5,-6};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
