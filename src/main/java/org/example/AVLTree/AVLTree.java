package org.example.AVLTree;

/**
 * AVL 树是一种自平衡二叉搜索树，由托尔·哈斯特罗姆在 1960 年提出并在 1962 年发表。
 * 的名字来源于发明者的名字：Adelson-Velsky 和 Landis，他们是苏联数学家，于 1962 年发表了一篇论文，详细介绍了 AVL 树的概念和性质。
 *
 * 在二叉搜索树中，如果插入的元素按照特定的顺序排列，可能会导致树变得非常不平衡，从而降低搜索、插入和删除的效率。
 * 为了解决这个问题，AVL 树通过在每个节点中维护一个平衡因子来确保树的平衡。平衡因子是左子树的高度减去右子树的高度。
 * 如果平衡因子的绝对值大于等于 2，则通过旋转操作来重新平衡树。
 *
 * AVL 树是用于存储有序数据的一种重要数据结构，它是二叉搜索树的一种改进和扩展。
 * 它不仅能够提高搜索、插入和删除操作的效率，而且还能够确保树的深度始终保持在 O(log n) 的水平。
 * 随着计算机技术的不断发展，AVL 树已经成为了许多高效算法和系统中必不可少的一种基础数据结构。
 */
public class AVLTree {
    AVLNode root;

    static class AVLNode {
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        int height=1;//树高度

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public AVLNode(int key) {
            this.key = key;
        }
    }

    public AVLTree(AVLNode root) {
        this.root = root;
    }

    /**
     * 返回节点的高度
     * @param node 节点
     * @return 节点属性的高度
     */
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    /**
     * 更新节点高度
     * @param node 节点
     */
    private void updateHeight(AVLNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * 平衡因子（balance factor） =左子树高度-右子树高度
     *  bf = 0，1，-1 时，表示左右平衡
     *  bf > 1 时，表示左边太高
     *  bf < -1 时，表示右边太高
     * @param node 节点
     * @return bf
     */
    private int balanceFactor(AVLNode node) {
        return height(node.left) - height(node.right);
    }

    /**
     * 树左旋转
     * @param red 要旋转的根节点
     * @return 旋转后的根节点
     */
    private AVLNode leftRotate(AVLNode red) {
        AVLNode yellow=red.right;
        red.right=yellow.left;
        yellow.left=red;
        updateHeight(red);
        updateHeight(yellow);
        return yellow;
    }

    /**
     * 树右旋转
     * @param red 要旋转的根节点
     * @return 旋转后的根节点
     */
    private AVLNode rightRotate(AVLNode red) {
        AVLNode yellow = red.left;
        /*
        AVLNode green = yellow.right;
        red.left = green;
        等价于下行
         */
        red.left = yellow.right;
        yellow.right = red;
        updateHeight(red);
        updateHeight(yellow);
        return yellow;
    }

    /**
     * 左右旋：先左旋转左子树，再右旋转树
     * @param node 要旋转的树根节点
     * @return 旋转后的根节点
     */
    private AVLNode leftRightRotate(AVLNode node) {
        node.left=leftRotate(node.left);
        return rightRotate(node);
    }

    /**
     * 右左旋：先右旋转右子树，再左旋转树
     * @param node 要旋转的树根节点
     * @return 旋转后的根节点
     */
    private AVLNode rightLeftRotate(AVLNode node) {
        node.right=rightRotate(node.right);
        return leftRotate(node);
    }

    /**
     * 根据不同的情况旋转使树平衡
     * @param node 需要平衡的树的根节点
     * @return 平衡后的树的根节点
     */
    private AVLNode balance(AVLNode node) {
        if(node==null) return null;
        int bf = balanceFactor(node);
        if(bf>1&&balanceFactor(node.left)>=0){//左左
            rightRotate(node);
        }else if (bf>1&&balanceFactor(node.left)<0) {//左右
            leftRightRotate(node);
        }else if(bf<1&&balanceFactor(node.right)>0){//右左
            rightLeftRotate(node);
        }else if(bf<1&&balanceFactor(node.right)<=0){//右右
            leftRightRotate(node);
        }
        return node;
    }

    /**
     * 树新增节点
     * @param key 键
     * @param value 值
     */
    public void put(int key, Object value) {
        root=doPut(root,key,value);
    }

    /**
     * 新增节点的逻辑方法
     * @param node 根节点
     * @param key 键
     * @param value 值
     * @return 根节点
     */
    private AVLNode doPut(AVLNode node, int key, Object value) {
        if(node==null)
            return new AVLNode(key,value);
        if(key==node.key){
            node.value=value;
        }else if(key<node.key){
            doPut(node.left,key,value);
        }else {
            doPut(node.right,key,value);
        }
        updateHeight(node);//更新节点的高度
        return balance(node);//平衡树并返回树的节点
    }

    /**
     * 删除指定key的节点
     * @param key 键
     */
    public void remove(int key){
        root=doRemove(root,key);
    }

    /**
     * 删除节点的逻辑
     * @param node 被删除的节点
     * @param key 键
     * @return 删除节点后的根节点
     */
    private AVLNode doRemove(AVLNode node, int key) {
        if(node==null)
            return null;
        if(key<node.key){
            doRemove(node.left,key);
        }else if(key>node.key){
            doRemove(node.right,key);
        }else {
            if(node.left==null){
                node=node.right;
            }else if(node.right==null){
                node=node.left;
            }else {
                //找到后继
                AVLNode successor=node.right;
                while(successor.left!=null){
                    successor=successor.left;
                }
                //删除后继节点
                successor.right=doRemove(node.right,successor.key);
                //将当前被删除节点的左子树赋值当前后继节点的左子树
                successor.left=node.left;

                node=successor;
            }
        }
        //高度更新
        updateHeight(node);
        //平衡节点
        return balance(node);
    }
}
