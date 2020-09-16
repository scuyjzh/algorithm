package com.scuyjzh.design;

import java.util.HashMap;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 *
 * @author scuyjzh
 * @date 2020/9/16 18:58
 */
class LRUCache {
    class Node {
        public int key, val;
        public Node next, prev;

        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

    class DoubleList {
        /**
         * 虚拟头尾节点
         */
        private Node head, tail;
        /**
         * 链表元素数
         */
        private int size;

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        /**
         * 在链表头部添加节点 x，时间 O(1)
         */
        public void addFirst(Node x) {
            x.next = head.next;
            x.prev = head;
            head.next.prev = x;
            head.next = x;
            size++;
        }

        /**
         * 删除链表中的 x 节点（x 一定存在），时间 O(1)
         */
        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        /**
         * 删除链表中最后一个节点，并返回该节点，时间 O(1)
         */
        public Node removeLast() {
            if (tail.prev == head) {
                return null;
            }
            Node last = tail.prev;
            remove(last);
            return last;
        }

        /**
         * 返回链表长度，时间 O(1)
         */
        public int size() {
            return size;
        }
    }

    /**
     * 缓存容量
     */
    private int cap;
    /**
     * key 映射到 Node(key, val)
     */
    private HashMap<Integer, Node> map;
    /**
     * Node(k1, v1) <-> Node(k2, v2)...
     */
    private DoubleList cache;

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        int val = map.get(key).val;
        // 利用 put 方法把该数据提前
        put(key, val);
        return val;
    }

    public void put(int key, int val) {
        // 先把新节点 x 做出来
        Node x = new Node(key, val);
        // key 已存在
        if (map.containsKey(key)) {
            // 删除旧的节点
            cache.remove(map.get(key));
        } else {
            // cache 已满
            if (cap == cache.size) {
                // 删除链表最后一个节点
                Node last = cache.removeLast();
                // 同时删除 map 中映射到该节点的 key
                map.remove(last.key);
            }
        }
        // 新节点 x 插到头部
        cache.addFirst(x);
        // 更新 map 中对应的数据
        map.put(key, x);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
