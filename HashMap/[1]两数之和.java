class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 计算当前元素需要的另一个元素
            int complement = target - nums[i];
            // 检查 HashMap 中是否存在这个元素
            if (map.containsKey(complement)) {
                // 如果存在，返回这两个元素的索引
                return new int[] { map.get(complement), i };
            }
            // 如果不存在，将当前元素及其索引存入 HashMap
            map.put(nums[i], i);
        }
        return new int[0];
    }
}