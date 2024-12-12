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

    /**
     * 二分查找平衡版 不奢望循环内通过 mid找出目标, 缩小区间直至剩 1 个, 剩下的这个可能就是要找的（通过 low）,左闭右开的区间，low 指向的可能是目标，而 high 指向的不是目标
     * @param arr 传入的数组
     * @param target 目标值
     * @return 找到则返回目标值，没有找到则返回-1
     */
    public static int binarySearchBalance(int[] arr, int target) {
        int low = 0;
        int high = arr.length ;
        while (1<high-low) {
            int mid = (low + high) >>>1;
            if(target<arr[mid]) {
                high = mid ;
            }
            else {
                low = mid;
            }
        }
        return (arr[low] == target) ? low : -1;
    }

    /**
     * 二分查找 java版
     * @param arr 传入的数组
     * @param fromIndex  起始指针
     * @param toIndex    终止指针
     * @param key        查找目标
     * @return  例如 $[1,3,5,6]$ 要插入 $2$ 那么就是找到一个位置，这个位置左侧元素都比它小，等循环结束，若没找到，low 左侧元素肯定都比 target 小，因此 low 即插入点
     */
    public static int binarySearchJava(int[] arr, int fromIndex, int toIndex, long key) {
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = (low + high) >>>1;
            long midVal = arr[mid];
            if(key<midVal) {
                high = mid - 1;
            }else if(key>midVal) {
                low = mid + 1;
            }else return mid;
        }
        return -(low+1);//-1 是为了把索引 0 位置的插入点与找到的情况进行区分
    }
}
