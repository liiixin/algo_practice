//给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。 
//
// 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。 
//
// 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿
//的周长。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
//输出：16
//解释：它的周长是上面图片中的 16 个黄色的边 
//
// 示例 2： 
//
// 
//输入：grid = [[1]]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：grid = [[1,0]]
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// row == grid.length 
// col == grid[i].length 
// 1 <= row, col <= 100 
// grid[i][j] 为 0 或 1 
// 
// Related Topics 哈希表 
// 👍 414 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 这道题用 DFS 来解并不是最优的方法。但这道题是一个很好的理解 DFS 遍历过程的例题
    public int islandPerimeter(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1){
                    // 题目限制只有一个岛屿，因此计算一个即可
                    return dfs(grid, i, j);
                }
            }
        }
        return 0;
    }
    private int dfs(int[][] grid, int row, int col){
        // 函数因为「坐标 (r, c) 超出网格范围」返回，代表边长为 1
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length){
            return 1;
        }
        // 函数因为「当前格子是海洋格子」返回，代表边长为 1
        if (grid[row][col] == 0) {
            return 1;
        }
        // 函数因为「当前格子是已遍历的陆地格子」返回，和周长没关系
        if (grid[row][col] != 1) {
            return 0;
        }
        // 将格子标记为 已遍历过
        grid[row][col] = 2;

        return dfs(grid, row + 1, col) + dfs(grid, row - 1, col)
                + dfs(grid, row, col + 1) + dfs(grid, row, col - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
