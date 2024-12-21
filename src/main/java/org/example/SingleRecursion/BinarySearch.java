package org.example.SingleRecursion;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(search(arr, 3, 0, arr.length - 1));
    }

    /**
     * 二分查找递归
     * @param arr 传入的数组
     * @param target 目标值
     * @param low 低位指针
     * @param high 高位指针
     * @return
     */
    public static int search(int[] arr, int target,int low,int high) {
        if(low>high)
            return -1;//没找到
        int mid=(low+high)>>>1;
        if(arr[mid]<target)
            return search(arr,target,mid+1,high);
        else if (arr[mid]>target)
            return search(arr,target,low,mid-1);
        else return mid;
    }
}
