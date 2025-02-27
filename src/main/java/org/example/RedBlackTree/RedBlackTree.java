package org.example.RedBlackTree;

/**
 * > 红黑树是一种自平衡二叉查找树，最早由一位名叫Rudolf Bayer的德国计算机科学家于1972年发明。
 * 然而，最初的树形结构不是现在的红黑树，而是一种称为B树的结构，它是一种多叉树，可用于在磁盘上存储大量数据。
 * >
 * > 在1980年代早期，计算机科学家Leonard Adleman和Daniel Sleator推广了红黑树，并证明了它的自平衡性和高效性。
 * 从那时起，红黑树成为了最流行的自平衡二叉查找树之一，并被广泛应用于许多领域，如编译器、操作系统、数据库等。
 * >
 * > 红黑树的名字来源于红色节点和黑色节点的交替出现，它们的颜色是用来维护树的平衡性的关键。
 * 它们的颜色具有特殊的意义，黑色节点代表普通节点，而红色节点代表一个新添加的节点，它们必须满足一些特定的规则才能维持树的平衡性。
 * <p>
 * 红黑树也是一种自平衡的二叉搜索树，较之 AVL，插入和删除时旋转次数更少
 * 1. 所有节点都有两种颜色：红:red_circle:、黑:black_circle:
 * 2. 所有 null 视为黑色:black_circle:
 * 3. 红色:red_circle:节点不能相邻
 * 4. 根节点是黑色:black_circle:
 * 5. 从根到任意一个叶子节点，路径中的黑色:black_circle:节点数一样
 */
public class RedBlackTree {

    RBTreeNode root;

    public RedBlackTree(RBTreeNode root) {
        this.root = root;
    }

    private enum Color {
        RED, BLACK
    }

    private static class RBTreeNode {
        int key;
        Object value;
        RBTreeNode left;
        RBTreeNode right;
        RBTreeNode parent;
        Color color = Color.RED;

        public RBTreeNode(int key, Object value, RBTreeNode left, RBTreeNode right, RBTreeNode parent, Color color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.color = color;
        }

        public RBTreeNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public RBTreeNode(int key) {
            this.key = key;
        }

        public RBTreeNode(Color color, int key) {
            this.color = color;
            this.key = key;
        }

        /**
         * 是否是左孩子
         *
         * @return 父亲节点不为空且父亲的左节点等于本身
         */
        public boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        /**
         * 找到当前节点的的叔叔并返回
         *
         * @return 如果当前节点为左孩子，返回父亲节点的父亲的右孩子，如果当前节点为右孩子，返回父亲节点的父亲的左孩子
         */
        public RBTreeNode uncle() {
            if (parent == null || parent.parent == null) return null;
            if (parent.isLeftChild()) {
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }

        /**
         * 找到当前节点的兄弟节点并返回
         *
         * @return 如果当前节点是左孩子，返回父亲的右孩子，如果当前节点是右孩子，返回父亲的左孩子
         */
        public RBTreeNode sibling() {
            if (parent == null) return null;
            if (this.isLeftChild()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }
    }

    /**
     * 判断节点是否为红色
     *
     * @param node 当前节点
     * @return 不为空且属性color等于红色
     */
    public boolean isRed(RBTreeNode node) {
        return node != null && node.color == Color.RED;
    }

    /**
     * 判断节点是否为黑色
     *
     * @param node
     * @return boolean
     */
    public boolean isBlack(RBTreeNode node) {
        return node == null && node.color == Color.BLACK;
//        return !isRed(node);
    }

    /**
     * 左旋转传入的根节点的树
     * 1、parent的处理，2、旋转后新的父子关系的处理
     * @param pink 传入的根节点
     */
    private void leftRotate(RBTreeNode pink) {
        RBTreeNode pinkParent = pink.parent;

        RBTreeNode yellow =pink.right;
        RBTreeNode green=yellow.left;
        if(green!=null){
            green.parent=yellow;
        }
        yellow.left=pink;
        yellow.parent=pinkParent;
        pink.right=green;
        pink.parent=yellow;

        if(pinkParent==null){
            root=yellow;
        }else if(pinkParent.left==pink){
            pinkParent.left=yellow;
        }else {
            pinkParent.right=yellow;
        }
    }

    /**
     * 右旋转传入的根节点的树
     * 1、parent的处理，2、旋转后新的父子关系的处理
     * @param pink 传入的根节点
     */
    private void rightRotate(RBTreeNode pink) {
        RBTreeNode pinkParent = pink.parent;

        RBTreeNode yellow = pink.left;
        RBTreeNode green = yellow.right;
        if(green!=null){
            green.parent=pink;
        }
        yellow.right = pink;
        yellow.parent=pinkParent;
        pink.left= green;
        pink.parent=yellow;

        if(pinkParent==null){
            root=yellow;
        }else if(pinkParent.left==pink){
            pinkParent.left=yellow;
        }else {
            pinkParent.right=yellow;
        }
    }

    /**
     * 插入一个节点<br>
     * 插入节点均视为红色:red_circle:
     * @param key 键
     * @param value 值
     */
    public void put(int key, Object value) {
        RBTreeNode p = root;
        RBTreeNode parent = null;
        //循环遍历找到要插入节点合适的父节点
        while (p != null) {
            parent = p;
            if (key < p.key) {
                p = p.left;
            } else if (key > p.key) {
                p = p.right;
            } else {//key相同则更新节点
                p.value = value;
                return;
            }
        }

        RBTreeNode inserted = new RBTreeNode(key, value);
        if (parent == null) {
            root = inserted;
        } else if (key < parent.key) {
            parent.left = inserted;
            inserted.parent = parent;
        } else {
            parent.right = inserted;
            inserted.parent = parent;
        }

        fixRedRed(inserted);
    }

    /**
     * 插入节点时红红相连修正
     * @param node 要修正的节点
     */
    private void fixRedRed(RBTreeNode node) {
        //情况一：插入节点为根节点，将根节点变黑:black_circle:
        if (root == node) {
            node.color = Color.BLACK;
            return;
        }
        //情况2：插入节点的父亲若为黑色:black_circle:，树的红黑性质不变，无需调整
        if (isBlack(node.parent)) {
            return;
        }

        /*情况3：叔叔为红色:red_circle:
        父亲变为黑色:black_circle:，为了保证黑色平衡，连带的叔叔也变为黑色:black_circle:
        祖父如果是黑色不变，会造成这颗子树黑色过多，因此祖父节点变为红色:red_circle:
        祖父如果变成红色，可能会接着触发红红相邻，因此对将祖父进行递归调整
         */
        RBTreeNode parent = node.parent;
        RBTreeNode uncle = node.uncle();
        RBTreeNode grandParent = parent.parent;

        if (isRed(uncle)) {
            parent.color = Color.BLACK;
            uncle.color = Color.BLACK;
            grandParent.color = Color.RED;
            fixRedRed(grandParent);
            return;
        }

        /*
        情况4：叔叔为黑色:black_circle:
        1、父亲为左孩子，插入节点也是左孩子，此时即 LL 不平衡；
        让父亲变黑:black_circle:，为了保证这颗子树黑色不变，将祖父变成红:red_circle:，但叔叔子树少了一个黑色
        祖父右旋，补齐一个黑色给叔叔，父亲旋转上去取代祖父，由于它是黑色，不会再次触发红红相邻
        2、父亲为左孩子，插入节点是右孩子，此时即 LR 不平衡
        父亲左旋，变成 LL 情况，按 1. 来后续处理
        3、父亲为右孩子，插入节点也是右孩子，此时即 RR 不平衡
        让父亲变黑:black_circle:，为了保证这颗子树黑色不变，将祖父变成红:red_circle:，但叔叔子树少了一个黑色
        祖父左旋，补齐一个黑色给叔叔，父亲旋转上去取代祖父，由于它是黑色，不会再次触发红红相邻
        4、父亲为右孩子，插入节点是左孩子，此时即 RL 不平衡
        父亲右旋，变成 RR 情况，按 3. 来后续处理
         */
        if (parent.isLeftChild() && node.isLeftChild()) {//LL左左失衡
            parent.color = Color.BLACK;
            grandParent.color = Color.RED;
            rightRotate(grandParent);
        } else if (parent.isLeftChild() && !node.isLeftChild()) {//LR
            leftRotate(parent);
            parent.color = Color.BLACK;
            grandParent.color = Color.RED;
            rightRotate(grandParent);
        } else if (!parent.isLeftChild() && !node.isLeftChild()) {//RR
            parent.color = Color.BLACK;
            grandParent.color = Color.RED;
            leftRotate(grandParent);
        } else {//RL
            rightRotate(parent);
            parent.color = Color.BLACK;
            grandParent.color = Color.RED;
            leftRotate(grandParent);
        }
    }
}
