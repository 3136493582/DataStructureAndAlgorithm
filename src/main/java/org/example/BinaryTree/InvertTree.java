package org.example.BinaryTree;

/**
 * 翻转二叉树
 */
public class InvertTree {
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    private void invert(TreeNode root) {
        if(root == null) return;
        //交换左节点和右节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        //递归交换
        invert(root.left);
        invert(root.right);
    }
}
