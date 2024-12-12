package org.example.BinarySearch;

public class BinarySearch {

    /**
     * 二分查找基础版，区间左闭右闭
     * @param arr 传入的数组
     * @param target 目标值
     * @return 找到则返回目标值，没有找到则返回-1
     */
    public static int binarySearchBase(int[] arr, int target) {
        int low = 0;//左边的指针位置
        int high = arr.length - 1;//右边的指针位置
        while (low <= high) {
            int mid = (low + high) >>>1;//不使用除尘向右移位以免溢出产生负数，除以2取中间指针
            if(target<arr[mid]) {//目标值在左边
                high = mid - 1;
            }else if(target>arr[mid]) {//目标值在右边
                low = mid + 1;
            }else {//找到目标值
                return mid;
            }
        }
        //没找到返回-1
        return -1;
    }

    /**
     * 二分查找进阶版本，左闭右开
     * @param arr 传入的数组
     * @param target 目标值
     * @return 找到则返回目标值，没有找到则返回-1
     */
    public static int binarySearchPlus(int[] arr, int target) {
        int low = 0;//左边的指针位置
        int high = arr.length ;//右边的指针位置
        while (low < high) {
            int mid = (low + high) >>>1;//不使用除尘向右移位以免溢出产生负数，除以2取中间指针
            if(target<arr[mid]) {//目标值在左边
                high = mid ;
            }else if(target>arr[mid]) {//目标值在右边
                low = mid + 1;
            }else {//找到目标值
                return mid;
            }
        }
        //没找到返回-1
        return -1;
    }
}
