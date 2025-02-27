package org.example.BinarySearchTree;

public class BSTNodeCom<T> {
    T key;//若希望任意类型作为key，则后续可以将其设计为comparable接口
    Object value;
    BSTNodeCom<T> left;
    BSTNodeCom<T> right;

    public BSTNodeCom(T key) {
        this.key = key;
        this.value = key;
    }

    public BSTNodeCom(T key, Object value) {
        this.key = key;
        this.value = value;
    }

    public BSTNodeCom(T key, Object value, BSTNodeCom<T> left, BSTNodeCom<T> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
