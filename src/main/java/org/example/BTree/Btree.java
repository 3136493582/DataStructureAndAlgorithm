package org.example.BTree;

import java.util.Arrays;

public class Btree {
    Node root;
    int degree;//树中节点最小度数
    final int MIN_KEY_NUMBER;//最小key数目
    final int MAX_KEY_NUMBER;//最大key数目

    private static class Node{
        int[] keys;//关键字
        Node[] children;//孩子
        int keyNumber;//有效关键字个数
        boolean isLeaf=true;//是否是叶子节点
        int degree;//最小度数（最小孩子数）

        public Node(int degree) {//degree>=2
            this.degree = degree;
            this.children=new Node[degree*2];
            this.keys=new int[degree*2-1];
            this.keyNumber=0;
        }

        @Override
        public String toString(){
            return Arrays.toString(Arrays.copyOfRange(keys,0,keyNumber));
        }

        //多路查找
        Node get(int key){
            int index=0;
            while (index<keyNumber){
                if(keys[index]==key){
                    return this;
                }
                if(keys[index]>key){
                    break;
                }
                index++;
            }
            if(isLeaf){
                return null;
            }
            return children[index].get(key);
        }

        //向指定keys索引index插入key
        void insert(int key,int index){
            //从后往前移动元素
//            for(int i=keyNumber-1;i>=index;i--){
//                keys[i+1]=keys[i];
//            }

//            System.arraycopy性能更高
//            src：源数组，即要复制的数组。
//            srcPos：源数组的起始位置（索引），从该位置开始复制。
//            dest：目标数组，即数据要复制到的数组。
//            destPos：目标数组的起始位置（索引），从该位置开始粘贴。
//            length：要复制的元素数量
            System.arraycopy(keys,index,keys,index+1,keyNumber-index);
            keys[index]=key;
            keyNumber++;
        }

        //向children指定索引处插入child
        void insertChild(Node child,int index){
            ////从后往前移动元素
            System.arraycopy(children,index,children,index+1,keyNumber-index);
            children[index]=child;
        }

        //删除指定index的key
        int removeKey(int index){
            int temp=keys[index];
            System.arraycopy(keys,index+1,keys,index,--keyNumber-index);
            return temp;
        }

        //移除最左边的key
        int removeLeftMostKey(){
            return removeKey(0);
        }

        //移除最右边的key
        int removeRightMostKey(){
            return removeKey(keyNumber-1);
        }

        //删除指定索引的孩子节点
        Node removeChild(int index){
            Node removedChild=children[index];
            System.arraycopy(children,index+1,children,index,keyNumber-index);
            children[keyNumber]=null;
            return removedChild;
        }

        //删除最左边的孩子
        Node removeLeftMostChild(){
            return removeChild(0);
        }

        //删除最右边的孩子
        Node removeRightMostChild(){
            return removeChild(keyNumber);
        }

        //返回index孩子左边的兄弟
        Node childLeftSibling(int index){
            return index>=0?children[index-1]:null;
        }

        //返回index孩子右边的兄弟
        Node childRightSibling(int index){
            return index==keyNumber?null:children[index+1];
        }

        //将当前节点的keys和children移动到目标节点
        void moveToLeft(Node target){
            int start=target.keyNumber;
            if(!isLeaf){
                for(int i=0;i<=keyNumber;i++){
                    target.children[start+i]=children[i];
                }
            }
            for(int i=0;i<keyNumber;i++){
                target.keys[target.keyNumber++]=keys[i];
            }
        }
    }

    public Btree() {
        this(2);
    }

    public Btree(int degree) {
        this.degree = degree;
        root = new Node(degree);
        this.MAX_KEY_NUMBER=2*degree-1;
        this.MIN_KEY_NUMBER=degree-1;
    }

    //是否已满
    public boolean isFull(Node node){
        return node.keyNumber==MAX_KEY_NUMBER;
    }

    //1、是否存在
    public boolean contains(int key){
        return root.get(key)!=null;
    }

    //2、新增
    public void put(int key){
        doPut(root,key,null,0);
    }

    /**
     * 插入逻辑
     * @param node 插入节点
     * @param key 键
     * @param parent 父亲节点
     * @param index 孩子索引
     */
    private void doPut(Node node, int key,Node parent,int index) {
        int i=0;
        //遍历查找
        while (i<node.keyNumber){
            if(node.keys[i]==key){
                return;//找到了更新
            }
            if(node.keys[i]>key){
                break;//没有插入位置，因为此时的index
            }
            i++;
        }
        if(node.isLeaf){
            node.insert(key,i);
            //上限
        }else {
            doPut(node.children[i],key,node,index);
            //上限
        }
        if(isFull(node)){
            split(node,parent,index);
        }
    }

    //节点满了之后进行分裂
    private void split(Node left,Node parent,int index){
        if(root==left){//分裂的是根节点
            Node newRoot=new Node(this.degree);
            newRoot.isLeaf=false;
            newRoot.insertChild(left,index);
            this.root=newRoot;
            parent=newRoot;
        }

        //1、创建right节点，把left中的degree之后的key移动过去
        Node right=new Node(this.degree);
        right.isLeaf=left.isLeaf;
        System.arraycopy(left.keys,degree,right.keys,0,degree-1);
        //把left中的degree之后的key移动过去
        if(!left.isLeaf){
            System.arraycopy(left.children,degree,right.children,0,degree);
        }

        right.keyNumber=degree-1;
        left.keyNumber=degree-1;
        //2、中间的key(t-1)插入到父亲节点
        int mid=left.keys[degree-1];
        parent.insert(mid,index);
        //3、right节点作为父亲节点的孩子
        parent.insertChild(right,index+1);
    }
    //3、删除
}
