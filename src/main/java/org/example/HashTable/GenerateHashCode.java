package org.example.HashTable;

public class GenerateHashCode {
    //java源码的hash=个位*31+十位*310+百位*3100
    public static void main(String[] args) {
        String s1="bac";
        String s2=new String("abc");

        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        // 原则：值相同的字符串生成相同的 hash 码, 尽量让值不同的字符串生成不同的 hash 码
    /*
    对于 abc  a * 100 + b * 10 + c
    对于 bac  b * 100 + a * 10 + c
     */
        int hash=0;
        for(int i=0;i<s1.length();i++){
            char c=s1.charAt(i);
            System.out.println((int) c);
            //(a*10 + b)*10 + c  ==>  a*100 + b*10 + c  2^5
            hash=(hash<<5)-hash+c;//hash*32等于左移5位，再-hash等价于*31,*一个质数更不容易造成hash冲突，减少概率
        }
        System.out.println(hash);
    }
}
