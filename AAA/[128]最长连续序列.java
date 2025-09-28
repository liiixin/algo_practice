//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。 
//
// 
//
// 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？ 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。 
//
// 示例 2： 
//
// 
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 104 
// -109 <= nums[i] <= 109 
// 
// Related Topics 并查集 数组 
// 👍 761 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n <= 1){
            return n;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }

        int maxLength = Integer.MIN_VALUE;
        for (int a : set) {
            if (set.contains(a - 1)){
                // 若 a - 1 存在于 list（set）中，则 a 不可能是左边界，直接跳过；
                continue;
            }else {
                // 若 a - 1 不在 list（set） 中，则 a 是一个左边界，
                // 则继续找 a + 1，a + 2 ... 是否存在于 list（set）集合中，并记录长度。
                int len = 0;
                while (set.contains(a++)){
                    len++;
                }
                maxLength = Math.max(maxLength, len);
            }
        }
        return maxLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
