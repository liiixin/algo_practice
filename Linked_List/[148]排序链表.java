//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
//
// 进阶： 
//
// 
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 5 * 104] 内 
// -105 <= Node.val <= 105 
// 
// Related Topics 排序 链表 
// 👍 1182 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    // 自底向上归并排序  空间复杂度为 O(1)
    public ListNode sortList(ListNode head) {
        if (head == null){
            return head;
        }

        // 1.首先从头向后遍历，统计链表长度
        int length = 0;
        ListNode node = head;
        while (node != null){
            length++;
            node = node.next;
        }

        // 2.初始化 引入dummynode
        ListNode dummynode = new ListNode(0);
        dummynode.next = head;

        // 3.每次将链表拆分成若干个长度为subLen的子链表，并按照每两个子链表一组进行合并
        for (int subLen = 1; subLen < length; subLen <<= 1){ // subLen每次左移一位（即sublen = sublen*2）
            ListNode prev = dummynode;
            ListNode curr = dummynode.next;  // curr用于记录拆分链表的位置

            while (curr != null){  // 如果链表没有被拆完
                // 3.1 拆分subLen长度的链表1
                ListNode head_1 = curr; // 第一个链表的头，即curr初始的位置
                for (int i = 1; i < subLen && curr != null && curr.next != null; i++) { // 拆分出长度为subLen的链表1
                    curr = curr.next;
                }

                // 3.2 拆分subLen长度的链表2
                ListNode head_2 = curr.next; // 第二个链表的头，即链表1尾部的下一个位置
                curr.next = null;  // 断开第一个链表和第二个链表的链接
                curr = head_2;     // 第二个链表头，重新赋值给curr
                for (int i = 1; i < subLen && curr != null && curr.next != null; i++) { // 再拆分出长度为subLen的链表2
                    curr = curr.next;
                }

                // 3.3 再次断开 第二个链表最后的next的链接
                ListNode next = null;
                if (curr != null){
                    next = curr.next;  // next用于记录 拆分完两个链表的结束位置
                    curr.next = null;
                }

                // 3.4 合并两个subLen长度的有序链表
                ListNode merged = mergeTwoLists(head_1, head_2);
                prev.next = merged;  // prev.next 指向排好序链表的头
                while (prev.next != null){  // while 循环，将 prev 移动到 subLen*2 的位置后去
                    prev = prev.next;
                }
                curr = next;          // next用于记录 拆分完两个链表的结束位置
            }
        }
        return dummynode.next; // 返回新排好序的链表
    }

    // 此处是Leetcode21 --> 合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1,ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode curr  = dummy;

        while(l1 != null && l2!= null){ // 退出循环的条件是走完了其中一个链表
            // 判断l1 和 l2大小
            if (l1.val < l2.val){
                // l1 小 ， curr指向l1
                curr.next = l1;
                l1 = l1.next;       // l1 向后走一位
            }else{
                // l2 小 ， curr指向l2
                curr.next = l2;
                l2 = l2.next;       // l2向后走一位
            }
            curr = curr.next;       // curr后移一位
        }

        // 退出while循环之后,比较哪个链表剩下长度更长,直接拼接在排序链表末尾
        if(l1 == null) curr.next = l2;
        if(l2 == null) curr.next = l1;

        // 最后返回合并后有序的链表
        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
