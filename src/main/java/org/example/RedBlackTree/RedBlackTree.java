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

    /**
     * 查找要删除的节点
     * @param key 要删除节点的key
     * @return 有则返回节点，没有则返回null
     */
    private RBTreeNode find(int key) {
        RBTreeNode p=root;
        while (p != null) {
            if(p.key<key){
                p=p.left;
            }else if(p.key>key){
                p=p.right;
            }else {
                return p;
            }
        }
        return null;
    }

    /**
     * 查找剩余节点
     * @param deleted 被删除的节点
     * @return 返回被删除节点后剩余节点的后继节点
     */
    private RBTreeNode findReplaced(RBTreeNode deleted){
       if(deleted.left==null&&deleted.right==null){
           return null;
       }

       if(deleted.right==null){
           return deleted.left;
       }

       if(deleted.left==null){
           return deleted.right;
       }

       RBTreeNode s=deleted.right;
       while (s.left!=null){
           s=s.left;
       }
       return s;
    }

    public void remove(int key){
        RBTreeNode deleted=find(key);
        if(deleted==null){
            return;
        }
        deRemove(deleted);
    }

    private void deRemove(RBTreeNode deleted) {
        RBTreeNode replaced=findReplaced(deleted);
        RBTreeNode parent=deleted.parent;
        //没有孩子
        if(replaced==null){
            //case1 只有根节点
            if(deleted==root){
                root=null;
            }else {
                //case2 删除的是黑会失衡，红色不会失衡，删的是黑，剩下的是红，剩下的这个红节点变黑
                if(isBlack(deleted)){
                    //复杂调整
                    fixBlackBlack(deleted);
                }else {
                    //简单调整
                }

                //删除逻辑
                if(deleted.isLeftChild()){
                    parent.left=null;
                }else {
                    parent.right=null;
                }
                deleted.parent=null;
            }
            return;
        }
        //一个孩子
        if(replaced.left==null||replaced.right==null){
            //case1 只有根节点
            if(deleted==root){
                root.key=replaced.key;
                root.value=replaced.value;
                root.left=root.right=null;
            }else {
                //删除逻辑
                if(deleted.isLeftChild()){
                    parent.left=replaced;
                }else {
                    parent.right=replaced;
                }
                replaced.parent=parent;
                deleted.parent=deleted.left=deleted.right=null;

                if(isBlack(deleted)&&isBlack(replaced)){
                    //复杂处理
                    fixBlackBlack(replaced);
                }else {
                    //红色叶子，任何处理
                }

            }
            return;
        }
        //两个孩子=>有一个孩子或者没有孩子（李代桃僵技巧）
        int k=deleted.key;
        deleted.key=replaced.key;
        replaced.key=k;

        Object v=deleted.value;
        deleted.value=replaced.value;
        replaced.value=v;

        deRemove(replaced);
    }

    /**
     * 删除节点和剩下节点都是黑:black_circle:，触发双黑，双黑意思是：少了一个黑
     * @param node 节点
     */
    private void fixBlackBlack(RBTreeNode node) {
        if(node==root){
            return;
        }
        RBTreeNode parent=node.parent;
        RBTreeNode sibling=node.sibling();
        /*case3 被调整节点的兄弟为红:red_circle:，此时两个侄子定为黑 :black_circle:
         * 删除节点是左孩子，父亲左旋
         * 删除节点是右孩子，父亲右旋
         * 父亲和兄弟要变色，保证旋转后颜色平衡
         * 旋转的目的是让黑侄子变为删除节点的黑兄弟，对删除节点再次递归，进入 case 4 或 case 5
        */
        if(isRed(sibling)){
            if(node.isLeftChild()){
                rightRotate(parent);
            }else {
                leftRotate(parent);
            }
            parent.color=Color.RED;
            sibling.color=Color.BLACK;
            fixBlackBlack(node);
            return;
        }

        /*case 4：被调整节点的兄弟为黑:black_circle:，两个侄子都为黑 :black_circle:
         * 将兄弟变红:red_circle:，目的是将删除节点和兄弟那边的黑色高度同时减少 1
         * 如果父亲是红:red_circle:，则需将父亲变为黑，避免红红，此时路径黑节点数目不变
         * 如果父亲是黑:black_circle:，说明这条路径还是少黑，再次让父节点触发双黑
        */
        if (sibling != null) {

            if(isBlack(sibling.left)&&isBlack(sibling.right)){

                if(isRed(parent)){
                    parent.color=Color.RED;
                }else {
                    fixBlackBlack(parent);
                }

            }else {
                /*case5 被调整节点的兄弟为黑:black_circle:，至少一个红:red_circle:侄子
                 * 如果兄弟是左孩子，左侄子是红:red_circle:，LL 不平衡
                 将来删除节点这边少个黑，所以最后旋转过来的父亲需要变成黑:black_circle:，平衡起见，左侄子也是黑:black_circle:
                 原来兄弟要成为父亲，需要保留父亲颜色
                 * 如果兄弟是左孩子，右侄子是红:red_circle:，LR 不平衡
                 将来删除节点这边少个黑，所以最后旋转过来的父亲需要变成黑:black_circle:
                 右侄子会取代原来父亲，因此它保留父亲颜色
                 兄弟已经是黑了:black_circle:，无需改变
                 * 如果兄弟是右孩子，右侄子是红:red_circle:，RR 不平衡
                 将来删除节点这边少个黑，所以最后旋转过来的父亲需要变成黑:black_circle:，平衡起见，右侄子也是黑:black_circle:
                 原来兄弟要成为父亲，需要保留父亲颜色
                 如果兄弟是右孩子，左侄子是红:red_circle:，RL 不平衡
                 * 将来删除节点这边少个黑，所以最后旋转过来的父亲需要变成黑:black_circle:
                 左侄子会取代原来父亲，因此它保留父亲颜色
                 兄弟已经是黑了:black_circle:，无需改变
                */
                if(sibling.isLeftChild()&&isRed(sibling.right)){//LL
                    rightRotate(parent);
                    sibling.left.color=Color.BLACK;
                    sibling.color=Color.RED;
                    parent.color=Color.BLACK;
                } else if (sibling.isLeftChild()&&isRed(sibling.right)) {//LR
                    sibling.right.color=parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                    parent.color=Color.BLACK;
                } else if (!sibling.isLeftChild() && isRed(sibling.right)) {//RR
                    leftRotate(parent);
                    sibling.right.color=Color.BLACK;
                    sibling.color=Color.RED;
                    parent.color=Color.BLACK;
                }else {//RL
                    sibling.left.color=parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                    parent.color=Color.BLACK;
                }
            }


        }else {
            //实际也不会出现，触发双黑后，兄弟节点不会为 null
            fixBlackBlack(parent);
        }

    }
}
