package org.example.BinarySearchTree;

/**
 * 二叉搜索树（也称二叉排序树）是符合下面特征的二叉树：
 * <p>
 * 1. 树节点增加 key 属性，用来比较谁大谁小，key 不可以重复
 * 2. 对于任意一个树节点，它的 key 比左子树的 key 都大，同时也比右子树的 key 都小
 */
public class BSTTree1 {
    BSTNode root;

    public BSTTree1(BSTNode root) {
        this.root = root;
    }

    /**
     * 根据键获取值
     *
     * @param key 键
     * @return 值
     */
    public Object get(int key) {
        return doGet(root, key);
//        return doGet1(root,key);
    }

    /**
     * 递归形式通过键找值
     *
     * @param node 节点
     * @param key  键
     * @return 查找到的节点值
     */
    private Object doGet(BSTNode node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            return doGet(node.left, key);
        } else if (key > node.key) {
            return doGet(node.right, key);
        } else {
            return node.value;
        }
    }

    /**
     * 非递归形式通过键找值
     *
     * @param node 节点
     * @param key  键
     * @return 查找到的节点值
     */
    private Object doGet1(BSTNode node, int key) {
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        /*
                              4
                            /   \
                           2     6
                          / \   / \
                         1   3  5
         */
        BSTTree1 tree = new BSTTree1(new BSTNode(4, 4, new BSTNode(2, 2, new BSTNode(1), new BSTNode(3)), new BSTNode(6, 6, new BSTNode(5), null)));
        System.out.println(tree.root.value);
        System.out.println(tree.get(2));
    }
}
