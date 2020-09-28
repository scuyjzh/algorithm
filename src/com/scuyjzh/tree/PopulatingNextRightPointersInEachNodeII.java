package com.scuyjzh.tree;

import java.util.*;

/**
 * 给定一个二叉树，填充它的每个 next 指针，让这个指针指向其下一个右侧结点。如果找不到下一个右侧结点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * @author scuyjzh
 * @date 2020/9/28 1:33
 */
class PopulatingNextRightPointersInEachNodeII {
    /**
     * Definition for a Node.
     */
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }

    /**
     * 方法一：层序遍历
     */
    public Node connect1(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            Node last = null;
            for (int i = 1; i <= n; ++i) {
                Node node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i != 1) {
                    last.next = node;
                }
                last = node;
            }
        }
        return root;
    }

    /**
     * 方法二：使用已建立的 next 指针
     */
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        // cur 我们可以把它看做是每一层的链表
        Node cur = root;
        while (cur != null) {
            // 遍历当前层的时候，为了方便操作在下一层前面添加一个哑结点
            // （注意这里是访问当前层的结点，然后把下一层的结点串联成链表）
            Node dummy = new Node(0);
            // pre 表示访下一层结点的前一个结点
            Node pre = dummy;
            // 然后开始遍历当前层的链表
            while (cur != null) {
                if (cur.left != null) {
                    // 如果当前结点的左子结点不为空，就让 pre 结点的 next 指向它
                    pre.next = cur.left;
                    // 然后再更新 pre
                    pre = pre.next;
                }
                // 同理参照左子树
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }
                // 继续访问这一行的下一个结点
                cur = cur.next;
            }
            // 把下一层串联成一个链表之后，让它赋值给 cur，
            // 后续继续循环，直到 cur 为空为止
            cur = dummy.next;
        }
        return root;
    }
}
