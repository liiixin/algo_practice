//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 假设你总是可以到达数组的最后一个位置。 
//
// 
//
// 示例 1: 
//
// 
//输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 示例 2: 
//
// 
//输入: [2,3,0,1,4]
//输出: 2
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 1000 
// 0 <= nums[i] <= 105 
// 
// Related Topics 贪心算法 数组 
// 👍 1020 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int jump(int[] nums) {
        // 题目假设你总是可以到达数组的最后一个位置。

        // 在遍历数组时，我们不访问最后一个元素，这是因为在访问最后一个元素之前，我们的边界一定大于等于最后一个位置，
        // 否则就无法跳到最后一个位置了。如果访问最后一个元素，在边界正好为最后一个位置的情况下，
        // 我们会增加一次「不必要的跳跃次数」，因此我们不必访问最后一个元素。

        int rightmost = 0; // 目前能跳到的最远位置
        int steps = 0;     // 跳跃次数
        int end = 0;       // 上次跳跃可达范围右边界（下次的最右起跳点）
        for (int i = 0; i < nums.length - 1; i++) {
            rightmost = Math.max(rightmost, i + nums[i]); // 维护当前能够到达的最大下标位置
            if (i == end){
                // 到达边界时，更新边界并将跳跃次数增加 1。
                end = rightmost;
                steps++;
            }
        }
        return steps;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
