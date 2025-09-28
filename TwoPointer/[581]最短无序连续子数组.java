//给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。 
//
// 请你找出符合题意的 最短 子数组，并输出它的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：nums = [2,6,4,8,10,9,15]
//输出：5
//解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4]
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 104 
// -105 <= nums[i] <= 105 
// 
//
// 
//
// 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？ 
// 
// 
// Related Topics 栈 贪心 数组 双指针 排序 单调栈 
// 👍 555 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/solution/shi-jian-chao-guo-100de-javajie-fa-by-zackqf/
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        // 这里的max和min是遍历过的值里面的最大和最小值，不是整个数组的
        int max = nums[0];
        int min = nums[len - 1];
        int left = 0;
        int right = -1;
        for (int i = 0; i < len; i++) {
            // 同时 从前往后 和 从后往前 遍历，分别得到要排序数组的右边界和左边界；
            if (max > nums[i]){
                right = i;
            }else {
                max = nums[i];
            }
            if (min < nums[len - 1 - i]){
                left = len - 1 - i;
            }else {
                min = nums[len - 1- i];
            }
        }
        return right - left + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
