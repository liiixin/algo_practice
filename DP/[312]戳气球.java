//有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。 
//
// 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i -
// 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。 
//
// 求所能获得硬币的最大数量。 
//
// 
//示例 1：
//
// 
//输入：nums = [3,1,5,8]
//输出：167
//解释：
//nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
//coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167 
//
// 示例 2： 
//
// 
//输入：nums = [1,5]
//输出：10
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 500 
// 0 <= nums[i] <= 100 
// 
// Related Topics 分治算法 动态规划 
// 👍 737 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        // 令 dp[i][j]表示填满开区间 (i,j)能得到的最多硬币数，
        // 那么边界条件是 i ≥ j−1，此时有 dp[i][j]=0
        int[][] dp = new int[n + 2][n + 2];
        // 创建一个辅助数组，并在首尾各添加1，方便处理边界情况
        int[] val = new int[n + 2];
        val[0] = val[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            val[i] = nums[i - 1];
        }
        // 利用小区间里已经算好的数字来算更大的区间
        // 最初的小区间是最后三个数[n - 1, n, n + 1]
        // 最后的大区间就是[0, n + 1]
        // i 表示开区间左端点
        for (int i = n - 1; i >= 0; i--) {
            // j 表示开区间右端点
            for (int j = i + 2; j <= n + 1; j++) {
                // k 表示开区间内的索引
                for (int k = i + 1; k < j; k++) {
                    // 当前戳破的气球产生的硬币数
                    int sum = val[i] * val[k] * val[j];
                    // 再加上左右两个区间的
                    sum += dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }
        return dp[0][n + 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
