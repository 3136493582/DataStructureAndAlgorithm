package org.example.HashTable;

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
    void put(int hash, Object key, Object value) {
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
}
