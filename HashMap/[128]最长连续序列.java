class Solution {
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return n;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }
        int maxLength = Integer.MIN_VALUE;
        for(int a: set) {
            if (set.contains(a - 1)) {
                // 若 a - 1 存在于 list（set）中，则 a 不可能是左边界，直接跳过；
                continue;
            } else {
                // 若 a - 1 不在 list（set） 中，则 a 是一个左边界，
                // 则继续找 a + 1，a + 2 ... 是否存在于 list（set）集合中，并记录长度。
                int len = 0;
                while (set.contains(a++)) {
                    len++;
                }
                maxLength = Math.max(maxLength, len);
            }
        }
        return maxLength;
    }
}