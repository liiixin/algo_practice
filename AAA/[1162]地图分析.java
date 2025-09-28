//你现在手里有一份大小为 N x N 的 网格 grid，上面的每个 单元格 都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，请你找出一个海洋单
//元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的。 
//
// 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - 
//x1| + |y0 - y1| 。 
//
// 如果网格上只有陆地或者海洋，请返回 -1。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[[1,0,1],[0,0,0],[1,0,1]]
//输出：2
//解释： 
//海洋单元格 (1, 1) 和所有陆地单元格之间的距离都达到最大，最大距离为 2。
// 
//
// 示例 2： 
//
// 
//
// 输入：[[1,0,0],[0,0,0],[0,0,0]]
//输出：4
//解释： 
//海洋单元格 (2, 2) 和所有陆地单元格之间的距离都达到最大，最大距离为 4。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length == grid[0].length <= 100 
// grid[i][j] 不是 0 就是 1 
// 
// Related Topics 广度优先搜索 图 
// 👍 201 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/bfs-de-shi-yong-chang-jing-zong-jie-ceng-xu-bian-l/
    public int maxDistance(int[][] grid) {
        int N = grid.length;
        Queue<int[]> queue = new ArrayDeque<>();
        // 将所有陆地格子放入队列中,多源BFS
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1){
                    queue.add(new int[]{i, j});
                }
            }
        }

        // 如果地图上只有陆地或者海洋，直接返回 -1
        if (queue.isEmpty() || queue.size() == N * N){
            return -1;
        }

        // 用一个 moves 数组存储相邻格子的四个方向：
        int[][] moves = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int distances = -1;  // 记录当前遍历的层数（距离）

        while (!queue.isEmpty()){
            distances++;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] node = queue.poll();
                int r = node[0];
                int c = node[1];
                for (int[] move : moves){
                    int r2 = r + move[0];
                    int c2 = c + move[1];
                    if (inArea(grid, r2, c2) && grid[r2][c2] == 0){
                        grid[r2][c2] = 2;  // 将遍历过的海洋格子标记为 2，避免重复遍历。
                        queue.add(new int[]{r2, c2});
                    }
                }
            }
        }
        return distances;
    }

    // 判断坐标(r, c)是否在网格中
    private boolean inArea(int[][] grid, int r, int c){
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
