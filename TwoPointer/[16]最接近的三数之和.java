//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
//。假定每组输入只存在唯一答案。 
//
// 
//
// 示例： 
//
// 输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 10^3 
// -10^3 <= nums[i] <= 10^3 
// -10^4 <= target <= 10^4 
// 
// Related Topics 数组 双指针 
// 👍 795 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        int closestNum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right){
                int threeSum = nums[left] + nums[right] + nums[i];
                if (Math.abs(threeSum - target) < Math.abs(closestNum - target)){
                    closestNum = threeSum;
                }
                if (threeSum == target){
                    // 如果等于，肯定是最接近的
                    return target;
                }else if (threeSum > target){
                    right--;
                }else { // threeSum < target
                    left++;
                }
            }
        }
        return closestNum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
