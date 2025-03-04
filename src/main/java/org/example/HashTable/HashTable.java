package org.example.HashTable;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * hashtable第一版，不用hash算法生成hash值，使用数组索引当作hash值
 */
public class HashTable {
    Entry[] table=new Entry[16];
    int size=0;
    float loadFactor=0.75f;//负载因子，是一个介于 0 和 1 之间的浮点数，用于衡量哈希表的填充程度。默认值通常为 0.75。
    int threshold=(int)(loadFactor*table.length);//threshold 属性用于决定何时进行扩容操作。当哈希表中的元素数量超过 threshold 时，哈希表会自动扩容，以减少冲突并保持性能。

    //节点类
    static class Entry{
        /* 求模运算替换为位运算
        - 前提：数组长度是 2 的 n 次方
        - hash % 数组长度 等价于 hash & (数组长度-1)
     */
        int hash;
        Object key;
        Object value;
        Entry next;

        public Entry(int hash, Object key, Object value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 根据hash码获取value
     * @param hash hash值
     * @param key 键
     * @return 找到返回value，没有返回null
     */
    Object get(int hash,Object key) {
        int index=hash&(table.length-1);//找到数组表头索引
        if(table[index]==null){
            return null;
        }
        Entry entry=table[index];
        while(entry!=null){//循环向下查找表记录
            if(entry.key.equals(key)){
                return entry.value;
            }
            entry=entry.next;
        }
        return null;
    }

    /**
     * 向hashtable存入新值，若存在key，则更新
     * @param hash hash地址
     * @param key 键
     * @param value 值
     */
    public void put(int hash, Object key, Object value) {
        int index=hash&(table.length-1);
        if(table[index]==null){
            //如果为空直接新增
            table[index]=new Entry(hash,key,value);
        }else {
            Entry entry=table[index];
            while (true){
                if(entry.key.equals(key)){
                    entry.value=value;//更新
                }
                if(entry.next==null){
                    break;
                }
                entry=entry.next;
            }
            entry.next=new Entry(hash,key,value);//新增
        }
        size++;
        if(size>threshold){
            //扩容
            resize();
        }
    }

    private void resize() {
        Entry[] newTable=new Entry[table.length<<1];//向右移一位等价于*2
         /*
                拆分链表，移动到新数组，拆分规律
                * 一个链表最多拆成两个
                * hash & table.length == 0 的一组
                * hash & table.length != 0 的一组
                                          p
                0->8->16->24->32->40->48->null
                            a
                0->16->32->48->null
                        b
                8->24->40->null
             */
        for(int i=0;i<table.length;i++){//循环拿到每个hash表的表头
            Entry p=table[i];//表头指针
            if(p!=null){
                Entry groupA=null;//A组尾插入指针
                Entry groupB=null;//B组尾插入指针
                Entry groupAHead=null;//A组头指针
                Entry groupBHead=null;//B组头指针
                while (p!=null){
                    if((p.hash&table.length)==0){
                        if(groupA!=null){
                            groupA.next=p;
                        }else {
                            groupAHead=p;
                        }
                        groupA=p;
                    }else {
                        if(groupB!=null){
                            groupB.next=p;
                        }else {
                            groupBHead=p;
                        }
                        groupB=p;
                    }
                    p=p.next;
                }
                //A组的表头索引位置不变，B组的表头索引位置为原index_+table.length
                if(groupA!=null){
                    groupA.next=null;
                    newTable[i]=groupAHead;
                }
                if(groupB!=null){
                    groupB.next=null;
                    newTable[i+table.length]=groupBHead;
                }
            }
        }

        table=newTable;
        threshold=(int)(loadFactor*table.length);
    }

    /**
     * 根据hash码删除，返回删除的value
     * @param hash hash值
     * @param key 键
     * @return 有则返回被删除的value,没有则返回null
     */
    Object remove(int hash,Object key){
        int index=hash&(table.length-1);
        if(table[index]==null){
            //如果没有，返回空
            return null;
        }
        Entry entry=table[index];
        Entry pre=null;
        while(entry!=null){
            if(entry.key.equals(key)){
                //找到了
                if(pre==null){
                    //链表头
                    table[index]=entry.next;
                }else {
                    //非链表头
                    pre.next=entry.next;
                }
                size--;
                return entry.value;
            }
            //pre指针和entry指针依次向下移动一位
            pre=entry;
            entry=entry.next;
        }
        return null;
    }

    /**
     * 打印hash表的分散性
     */
    public void print(){
        int[] sums=new int[table.length];
        //拿到每个表头并统计每个链表的数目
        for(int i=0;i<table.length;i++){
            Entry p=table[i];
            while(p!=null){
                sums[i]++;
                p=p.next;
            }
        }

        System.out.println(Arrays.toString(sums));

        //将sums数组收集到的数据进行分组统计
        Map<Integer, Long> collect = Arrays.stream(sums).boxed().collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        System.out.println(collect);
    }
}
