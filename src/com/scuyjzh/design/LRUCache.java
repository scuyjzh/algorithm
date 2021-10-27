package com.scuyjzh.design;

import java.util.*;

/**
 * LRU 缓存机制
 *
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 *   • LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU
 *     缓存。
 *   • int get(int key) 如果关键字 key 存在于缓存中，则返回关键字
 *     的值，否则返回 -1 。
 *   • void put(int key, int value) 如果关键字已经存在，则变更其数
 *     据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量
 *     达到上限时，它应该在写入新数据之前删除最久未使用的数据值，
 *     从而为新的数据值留出空间。
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
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
         * 虚拟头、尾结点
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
         * 在链表头部添加结点 x，时间复杂度 O(1)
         */
        public void addFirst(Node x) {
            x.next = head.next;
            x.prev = head;
            head.next.prev = x;
            head.next = x;
            size++;
        }

        /**
         * 删除链表中的 x 结点（x 一定存在），时间复杂度 O(1)
         */
        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        /**
         * 删除链表中最后一个结点，并返回该结点，时间复杂度 O(1)
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
         * 返回链表长度，时间复杂度 O(1)
         */
        public int size() {
            return size;
        }
    }

    /**
     * 缓存容量
     */
    private int capacity;
    /**
     * key 映射到 Node(key, val)
     */
    private HashMap<Integer, Node> map;
    /**
     * Node(k1, v1) <-> Node(k2, v2)...
     */
    private DoubleList cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
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
        // 先把新结点 x 做出来
        Node x = new Node(key, val);
        // key 已存在
        if (map.containsKey(key)) {
            // 删除旧的结点
            cache.remove(map.get(key));
        } else {
            // cache 已满
            if (capacity == cache.size) {
                // 删除链表最后一个结点
                Node last = cache.removeLast();
                // 同时删除 map 中映射到该结点的 key
                map.remove(last.key);
            }
        }
        // 新结点 x 插到头部
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
