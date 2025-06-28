class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int k = 0; k < nums.length - 2; k++) {
            if (nums[k] > 0) {
                break;  // 剪枝：最小数大于 0，后续不可能有三数和为 0
            }
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;  // 跳过重复的数
            }
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum < 0) {
                    while (i < j && nums[i] == nums[++i]); // 增大和的值，移动左指针 i, 如果当前元素和前一个元素相同，直接跳过
                } else if (sum > 0) {
                    while (i < j && nums[j] == nums[--j]);
                } else {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
                    while (i < j && nums[i] == nums[++i]); // 跳过重复值
                    while (i < j && nums[j] == nums[--j]);
                }
            }
        }
        return res;
    }
}