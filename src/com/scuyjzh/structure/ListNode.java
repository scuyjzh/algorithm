package com.scuyjzh.structure;

/**
 * @author scuyjzh
 * @date 2020/8/25 10:54
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static ListNode initList(String str) {
        if ("[]".equals(str)) {
            return null;
        }
        str = str.substring(1, str.length() - 1);
        String[] split = str.split(",");
        int len = split.length;
        ListNode[] listNodes = new ListNode[len + 1];
        listNodes[0] = new ListNode(Integer.parseInt(split[0]));
        for (int i = 1; i < len; i++) {
            listNodes[i] = new ListNode(Integer.parseInt(split[i]));
            listNodes[i - 1].next = listNodes[i];
        }
        return listNodes[0];
    }

    public static String toString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (head.next != null) {
            sb.append(head.val).append(",");
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        return sb.toString();
    }
}
