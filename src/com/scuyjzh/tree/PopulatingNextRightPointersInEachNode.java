package com.scuyjzh.tree;

import java.util.*;

/**
 * 给定一个完美二叉树，其所有叶子结点都在同一层，每个父结点都有两个子结点。
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧结点。如果找不到下一个右侧结点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * @author scuyjzh
 * @date 2020/9/28 0:55
 */
class PopulatingNextRightPointersInEachNode {
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
        // Initialize a queue data structure which contains just the root of the tree
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        // Outer while loop which iterates over each level
        while (queue.size() > 0) {
            // Note the size of the queue
            int size = queue.size();
            // Iterate over all the nodes on the current level
            for (int i = 0; i < size; ++i) {
                // Pop a node from the front of the queue
                Node node = queue.poll();
                // This check is important. We don't want to establish any wrong connections.
                // The queue will contain nodes from 2 levels at most at any point in time.
                // This check ensures we only don't establish next pointers beyond the end of a level
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                // Add the children, if any, to the back of the queue
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        // Since the tree has now been modified, return the root node
        return root;
    }

    /**
     * 方法二：使用已建立的 next 指针
     */
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        // 从根结点开始，由于第 0 层只有这一个结点，所以不需要连接
        Node leftmost = root;
        // 如果当前最左结点的左孩子不存在，说明已经到达该树的最后一层，完成了所有结点的连接
        while (leftmost.left != null) {
            // 遍历某一层的结点时，这层结点的 next 指针已经建立
            // 因此我们只需要知道这一层的最左结点，就可以按照链表方式遍历，不需要使用队列
            Node head = leftmost;
            while (head != null) {
                // CONNECTION 1：
                // 两个子结点属于同一个父结点，因此直接通过父结点建立两个子结点的 next 指针即可
                head.left.next = head.right;
                // CONNECTION 2：
                // 连接不同父结点之间的子结点。更具体地说，连接的是第一个父结点的右孩子和第二父结点的左孩子
                // 由于已经在父结点这一层建立了 next 指针，因此可以直接通过第一个父结点的 next 指针找到第二个父结点，然后在它们的孩子之间建立连接
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                // Progress along the list (nodes on the current level)
                head = head.next;
            }
            // 完成当前层的连接后，进入下一层重复操作，直到所有的结点全部连接
            // 进入下一层后需要更新最左结点，然后从新的最左结点开始遍历该层所有结点
            // 因为是完美二叉树，因此最左结点一定是当前层最左结点的左孩子
            leftmost = leftmost.left;
        }
        return root;
    }

    /**
     * 方法三：使用已建立的 next 指针（递归法）
     */
    public Node connect3(Node root) {
        return helper(root);
    }

    private Node helper(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.right != null) {
            root.right.next = root.next == null ? null : root.next.left;
        }
        helper(root.left);
        helper(root.right);
        return root;
    }
}
