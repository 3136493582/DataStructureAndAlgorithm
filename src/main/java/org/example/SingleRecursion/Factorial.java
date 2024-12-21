package org.example.SingleRecursion;

/**
 * 递归求n的阶乘
 */
public class Factorial {
    public static int factorial(int n) {
        return n == 1 ? 1 : n * factorial(n - 1);
    }
    public static void main(String[] args) {
        int n=3;
        n=factorial(n);
        System.out.println(n);
    }
}
