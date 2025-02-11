package org.example.BinaryTree;

/**
 * 递归实现
 */
public class TreeTraversal {
    public static void main(String[] args) {
        /**
         *           1
         *         /   \
         *        2     3
         *       /     / \
         *      4     5   6
         */
        TreeNode root=new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3, new TreeNode(5), new TreeNode(6)));

        preOrderTraversal(root);
        System.out.println();
        inOrderTraversal(root);
        System.out.println();
        postOrderTraversal(root);
    }

    /**
     * 前序遍历
     * @param node 根节点
     */
    static void preOrderTraversal(TreeNode node) {
        if(node==null) return;
        System.out.print(node.value + "\t");
        preOrderTraversal(node.left);//左
        preOrderTraversal(node.right);//右
    }


    /**
     * 中序遍历
     * @param node 根节点
     */
    static void inOrderTraversal(TreeNode node) {
        if(node==null) return;
        inOrderTraversal(node.left);//左
        System.out.print(node.value + "\t");
        inOrderTraversal(node.right);//右
    }

    /**
     * 后序遍历
     * @param node 根节点
     */
    static void postOrderTraversal(TreeNode node) {
        if(node==null) return;
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.value + "\t");
    }
}
