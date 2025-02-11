package org.example.BinaryTree;

/**
 * 用来检查是否是对称二叉树
 */
public class IsSymmetric {
    /**
     * 检查是否是对称二叉树
     * @param root 根节点
     * @return 是返回true，不是返回false
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        //同时为null
        if (left == null && right == null) {
            return true;
        }
        //其中有一个为null
        if (left == null || right == null) {
            return false;
        }
        if(left.value!=right.value){
            return false;
        }
        return check(left.left, right.right) && check(left.right, right.left);
    }
}
