class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        // 使用HashMap存储前缀和及其出现次数
        HashMap<Integer, Integer> map = new HashMap<>();
        // 初始化：前缀和为0出现1次（表示空数组）
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            // 累计前缀和
            pre += nums[i];

            // 如果存在前缀和为(pre - k)的情况
            // 说明从该前缀和位置到当前位置的子数组和为k
            if (map.containsKey(pre - k)){
                // 将该前缀和出现的次数累加到结果中
                count += map.get(pre - k);
            }

            // 更新当前前缀和的出现次数
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
