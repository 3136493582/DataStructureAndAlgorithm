package org.example.BinarySearchTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * - Pre-order, NLR 前序遍历
 * - In-order, LNR 中序遍历
 * - Post-order, LRN 后序遍历
 * - Reverse pre-order, NRL 翻转前序遍历
 * - Reverse in-order, RNL 翻转中序遍历
 * - Reverse post-order, RLN 翻转后序遍历
 * @param <T>
 */
public class BSTTree1Com<T extends Comparable<T>> {
    BSTNodeCom<T> root;

    public BSTTree1Com(BSTNodeCom<T> root) {
        this.root = root;
    }

    /**
     * 根据键查找值
     * @param key 健
     * @return 值
     */
    public Object get(T key) {
        return doGet(root,key);
    }

    /**
     * 以递归方式查找节点
     * @param node 节点
     * @param key 键
     * @return 节点值
     */
    private Object doGet(BSTNodeCom<T> node, T key) {
        if (node == null) return null;
        int result = key.compareTo(node.key);
        if (result > 0) {
            return doGet(node.left,key);
        }else if (result < 0) {
            return doGet(node.right,key);
        }else {
            return node.value;
        }
    }

    /**
     * 得到树的最小节点值
     * @return 最小节点值
     */
    public Object min(){
        return doMin(root);
    }

    /**
     * 递归形式查找最小节点值
     * @param node 节点
     * @return 最小节点值
     */
    private Object doMin(BSTNodeCom<T> node) {
        if (node == null) return null;
        if(node.left == null){
            return node.value;
        }else {
            return doMin(node.left);
        }
    }

    /**
     * 返回树节点最大值
     * @return 节点最大值
     */
    public Object max(){
        return doMax(root);
    }

    /**
     * 递归方式查找节点最大值
     * @param node 节点
     * @return 最大值
     */
    private Object doMax(BSTNodeCom<T> node) {
        if (node == null) return null;
        if(node.right == null){
            return node.value;
        }else {
            return doMax(node.right);
        }
    }

    /**
     * 插入节点
     * @param key 键
     * @param value 值
     * @return 节点类
     */
    public BSTNodeCom<T> put(T key, Object value) {
        return doPut(root,key,value);
    }

    /**
     * 以递归方式插入
     * @param node 节点
     * @param key 键
     * @param value 值
     * @return 节点类
     */
    private BSTNodeCom<T> doPut(BSTNodeCom<T> node, T key, Object value) {
        if (node == null) return new BSTNodeCom<>(key,value);
        int result = key.compareTo(node.key);
        if (result > 0) {
            node.left = doPut(node.left,key,value);
        }else if (result < 0) {
            node.right = doPut(node.right,key,value);
        }else {
            node.value = value;
        }
        return node;
    }

    /**
     * 以非递归的方式新增
     * @param key 键
     * @param value 值
     */
    public void put1(T key, Object value) {
        BSTNodeCom<T> node = root;
        BSTNodeCom<T> parent = null;
        while (node != null) {
            parent = node;
            if (key.compareTo(node.key) > 0) {
                node = node.left;
            }else if (key.compareTo(node.key) < 0) {
                node = node.right;
            }else {
                //1、key存在则更新
                node.value = value;
                return;
            }
        }
        //2、不存在就新增
        if (parent == null) {//parent为空表示树是空的
            root = new BSTNodeCom<>(key,value);
        }else if(key.compareTo(parent.key)>0){
            parent.left = new BSTNodeCom<>(key,value);
        }else {
            parent.right = new BSTNodeCom<>(key,value);
        }
    }

    /**
     * 查找指定key节点的前驱
     * @param key 键
     * @return 前驱
     */
    public Object predecessor(T key) {
        BSTNodeCom<T> ancestorFromLeft;//记录查找指定节点前的最后一个祖先节点的指针
        BSTNodeCom<T>  p ;
        p=root;
        ancestorFromLeft=null;
        //查找指定节点
        while (p != null) {
            if (key.compareTo(p.key) >0) {
                p = p.left;
            } else if (key.compareTo(p.key)<0) {
                ancestorFromLeft = p;
                p=p.right;
            }else {
                break;
            }
        }

        //指针为空树中没有指定节点
        if (p == null) return null;

        //1、节点有左子树，此时前驱节点就是左子树的最大值，图中属于这种情况的有
        if(p.left != null){
            return doMax(p.left);
        }

        //2、节点没有左子树，若离它最近的祖先自从左而来，此祖先即为前驱，如
        return ancestorFromLeft!=null?ancestorFromLeft.key:null;
    }

    /**
     * 查找指定key节点的后继
     * @param key 键
     * @return 指定节点的后继
     */
    public Object successor(T key) {
        BSTNodeCom<T> ancestorFromRight;
        ancestorFromRight = null;
        BSTNodeCom<T>  p;
        p=root;
        while (p != null) {
            if (key.compareTo(p.key) > 0) {
                ancestorFromRight = p;
                p = p.left;
            }else if (key.compareTo(p.key)<0) {
                p = p.right;
            }else {
                break;
            }
        }

        if (p == null) return null;

        if(p.right != null){
            return doMin(p.right);
        }
        return ancestorFromRight!=null?ancestorFromRight.key:null;
    }

    /**
     * 根据关键字删除
     * @param key 键
     * @return 被删除的key节点值
     */
    public Object delete(T key) {
        BSTNodeCom<T> p = root;//被删除节点的指针
        BSTNodeCom<T> parent = null;//父亲节点
        while (p != null) {//查找被删除的节点
            if (key.compareTo(p.key) > 0) {
                parent = p;
                p = p.left;
            }else if (key.compareTo(p.key)<0) {
                parent = p;
                p = p.right;
            }else{
                break;
            }
        }

        if (p == null) return null;

        if(p.left==null){//1、删除节点没有左孩子，将右孩子托孤给 Parent
            shift(parent,p,p.right);
        } else if (p.right==null) {//2、删除节点没有右孩子，将左孩子托孤给 Parent 3、删除节点左右孩子都没有，已经被涵盖在情况1、情况2 当中，把 null托孤给Parent
            shift(parent,p,p.left);
        }else {//4、删除节点左右孩子都有

            //一、找后继节点和后继节点的父亲
            BSTNodeCom<T> s=p.right;
            BSTNodeCom<T> sParent = p;
            while (s.left != null) {
                sParent = s;
                s = s.left;
            }

            //2、如果删除的节点和后继不相邻，处理后继的后事
            if(sParent!=p){
                shift(sParent,s,s.right);//把后继的右子树托孤给后继父亲
                s.right=p.left;//后继的右子树为被删除节点的右子树
            }


            s.left=p.left;//后继的左子树为被删除节点的左子树
            shift(parent,p,s);//如果不相邻，把后继作为根节点；如果相邻，被删除节点的父亲左子树为被删除节点的左子树
        }
        return p.value;
    }

    /**
     * 托孤方法
     * @param parent 父亲节点
     * @param deleted 被删除的节点
     * @param child 子节点
     */
    private void shift(BSTNodeCom<T> parent, BSTNodeCom<T> deleted,BSTNodeCom<T> child) {
        if (parent == null) {
            root = deleted;
        }else if (deleted==parent.left) {
            parent.left=child;
        }else {
            parent.right=child;
        }
    }

    /**
     * 查找比指定key小的节点集合
     * @param key 键
     * @return 比key小的节点值集合
     */
    public List<Object> less(T key) {
        ArrayList<Object> result = new ArrayList<>();//结果集合
        BSTNodeCom<T> p = root;
        LinkedList<BSTNodeCom<T>> stack = new LinkedList<>();//定义栈用来记录节点路径
        while (p != null||!stack.isEmpty()) {
            if(p!=null){
                stack.push(p);
                p=p.left;
            }else {
                BSTNodeCom<T> pop = stack.pop();
                if(pop.key.compareTo(key)>0){
                    result.add(pop.value);
                }else {
                    break;
                }
                p=pop.right;
            }
        }
        return result;
    }

    /**
     * 查找比指定key大的节点集合
     * @param key 键
     * @return 节点值集合
     */
    public List<Object> greater(T key) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNodeCom<T> p = root;
        LinkedList<BSTNodeCom<T>> stack = new LinkedList<>();
        while (p != null||!stack.isEmpty()) {
            if(p!=null){
                stack.push(p);
                p=p.right;
            }else {
                BSTNodeCom<T> pop = stack.pop();
                if(pop.key.compareTo(key)<0){
                    result.add(pop.value);
                }else {
                    break;
                }
                p=pop.left;
            }
        }
        return result;
    }

    /**
     * 查找大小在两个键之间的节点值集合
     * @param key1 键1
     * @param key2 键2
     * @return 大小在两个键之间的节点值集合
     */
    public List<Object> bettween(T key1,T key2) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNodeCom<T> p = root;
        LinkedList<BSTNodeCom<T>> stack = new LinkedList<>();
        while (p != null||!stack.isEmpty()) {
            if(p!=null){
                stack.push(p);
                p=p.left;
            }else {
                BSTNodeCom<T> pop = stack.pop();
                if(pop.key.compareTo(key1)<0&&pop.key.compareTo(key2)>0){
                    result.add(pop.value);
                }else {
                    break;
                }
                p=pop.right;
            }
        }
        return result;
    }
}
