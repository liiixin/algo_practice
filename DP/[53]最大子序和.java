class Solution {
    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for (int num: nums) {
            if (sum > 0){
                sum += num; // sum 对结果有增益效果，则 sum 保留并加上当前遍历数字
            }else{
                sum = num;  // sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
            }
            ans = Math.max(ans, sum); // 将最大值置为ans
        }
        return ans;
    }
}