//在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：matrix = [["0","1"],["1","0"]]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：matrix = [["0"]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 300 
// matrix[i][j] 为 '0' 或 '1' 
// 
// Related Topics 动态规划 
// 👍 739 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 两个版本，最后优化为一维
    public int maximalSquare(char[][] matrix) {
        if((matrix == null) || matrix.length < 1 || matrix[0].length < 1){
            return 0;
        }
        int height = matrix.length;
        int width = matrix[0].length;
        int maxSide = 0;

        // 预处理新增第一行、第一列均为0
        // dp(i, j) 是以 matrix(i - 1, j - 1) 为 右下角 的正方形的最大边长。
        // int[][] dp = new int[height + 1][width + 1];
        int[] dp = new int[width + 1];
        int northwest = 0;

        //for (int row = 0; row < height; row++) {
        for(char[] chars : matrix){
            northwest = 0;  // 遍历每行时，还原回辅助的原值0
            for (int col = 0; col < width; col++) {
                int nextNorthwest = dp[col + 1];
                //if (matrix[row][col] == '1'){
                    // 若某格子值为 1，则以此为右下角的正方形的、最大边长为：
                    // 上面的正方形、左面的正方形或左上的正方形中，最小的那个，再加上此格。
                    //dp[row + 1][col + 1] = Math.min(Math.min(dp[row + 1][col], dp[row][col + 1]), dp[row][col]) + 1;
                    //maxSide = Math.max(maxSide, dp[row + 1][col + 1]);
                if(chars[col] == '1'){
                    dp[col + 1] = Math.min(Math.min(dp[col], dp[col + 1]), northwest) + 1;
                    maxSide = Math.max(maxSide, dp[col + 1]);
                }else{
                    dp[col + 1] = 0;
                }
                northwest = nextNorthwest;
            }
        }
        return maxSide * maxSide;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
