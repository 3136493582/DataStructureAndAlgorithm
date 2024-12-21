package org.example.SingleRecursion;

/**
 * 反向打印字符串
 */
public class ReversePrintStr {
    public static void reversePrint(String str,int index) {
        if(index==str.length())
            return ;
        reversePrint(str,index+1);
        System.out.print(str.charAt(index));
    }
    public static void main(String[] args) {
        String str = "Hello World";
        reversePrint(str,6);
    }
}
