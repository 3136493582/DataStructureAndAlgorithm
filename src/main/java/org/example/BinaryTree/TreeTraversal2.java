package org.example.BinaryTree;

import org.example.Stack.LinkedListStack;

/**
 * 非递归实现
 */
public class TreeTraversal2 {
    public static void main(String[] args) {
        /**
         *           1
         *         /   \
         *        2     3
         *       /     / \
         *      4     5   6
         */
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3, new TreeNode(5), new TreeNode(6)));

//        preOrderTraversal(root);
//        System.out.println();
//        inOrderTraversal(root);
//        System.out.println();
        postOrderTraversal(root);
    }

    /**
     * 前序遍历
     * @param node 根节点
     */
    static void preOrderTraversal(TreeNode node) {
        LinkedListStack<TreeNode> stack = new LinkedListStack<TreeNode>(10);
        TreeNode current = node;
        while (current != null || !stack.isEmpty()) {//栈不为空或者当前节点不为空则遍历
            if (current != null) {
                stack.push(current);//压栈
                System.out.println("去"+current.value);
                current = current.left;
            }else {
                current = stack.pop();//弹出栈
//                System.out.println("回"+current.value);
                current = current.right;
            }
        }
    }


    /**
     * 中序遍历
     * @param node 根节点
     */
    static void inOrderTraversal(TreeNode node) {
        LinkedListStack<TreeNode> stack = new LinkedListStack<TreeNode>(10);
        TreeNode current = node;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
//                System.out.println("去"+current.value);
                current = current.left;
            }else {
                current = stack.pop();
                System.out.println("回"+current.value);
                current = current.right;
            }
        }
    }

    /**
     * 后序遍历
     * @param node 根节点
     */
    static void postOrderTraversal(TreeNode node) {
        LinkedListStack<TreeNode> stack = new LinkedListStack<TreeNode>(10);
        TreeNode current = node;
        TreeNode pop = null;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            }else {
                TreeNode peek=stack.peek();
                if(peek.right==null || peek.right==pop){
                    pop=stack.pop();
                    System.out.println(pop.value);
                }else {
                    current=peek.right;
                }
            }
        }
    }
}
