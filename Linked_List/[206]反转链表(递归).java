//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5]
//输出：[5,4,3,2,1]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：[2,1]
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
// 链表中节点的数目范围是 [0, 5000] 
// -5000 <= Node.val <= 5000 
// 
//
// 
//
// 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？ 
// 
// 
// Related Topics 链表 
// 👍 1801 👎 0


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
    // 递归的时间复杂度：O(n)，其中 n 是链表的长度。需要遍历链表一次。
    // 空间复杂度：O(n)
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        // 调用递推公式反转当前结点之后的所有节点
        // 返回的结果是反转后的链表的头结点

        // 想象递归已经层层返回，到了最后一步
        // 以链表 1->2->3->4->5 为例，现在链表变成了 5->4->3->2->null，1->2->null（是一个链表，不是两个链表）
        // 此时 newHead是5，head是1
        ListNode newHead = reverseList(head.next);
        // 最后的操作是把链表 1->2->null 变成 2->1->null
        // head是1，head.next是2，head.next.next = head 就是2指向1，此时链表为 2->1->2
        head.next.next = head;
        // 防止链表循环，1指向null，此时链表为 2->1->null，整个链表为 5->4->3->2->1->null
        head.next = null;
        return newHead;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
