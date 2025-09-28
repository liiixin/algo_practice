//你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。 
//
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
//示如果要学习课程 ai 则 必须 先学习课程 bi 。 
//
// 
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。 
// 
//
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：true
//解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。 
//
// 示例 2： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
//输出：false
//解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。 
//
// 
//
// 提示： 
//
// 
// 1 <= numCourses <= 105 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// prerequisites[i] 中的所有课程对 互不相同 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 
// 👍 842 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // https://leetcode-cn.com/problems/course-schedule/solution/course-schedule-tuo-bu-pai-xu-bfsdfsliang-chong-fa/
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        // 初始化邻接矩阵
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());  // 添加numCourses个数组
        }
        int[] flags = new int[numCourses];  // 每个课程对应的flag
        for (int[] cp : prerequisites){
            adjacency.get(cp[1]).add(cp[0]); // 把每个匹配单独取出来
            // cp[1] 是前导课程，然后访问adj的第cp[1]个位置，然后添加cp[0]
        }
        for (int i = 0; i < numCourses; i++) {
            // 依次遍历adj每个位置
            if (!dfs(adjacency, flags, i)){
                return false;
            }
        }
        return true;
    }
    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i){
        // 借助一个标志列表 flags，用于判断每个节点 i （课程）的状态：
        // 未被 DFS 访问：i == 0；
        // 已被其他节点启动的 DFS 访问：i == -1；
        // 已被当前节点启动的 DFS 访问：i == 1。

        if (flags[i] == 1){
            return false; // 说明在本轮 DFS 搜索中节点 i 被第 2 次访问，即 课程安排图有环 ，直接返回 False。
        }
        if (flags[i] == -1){
            return true; // 说明当前访问节点已被其他节点启动的 DFS 访问，无需再重复搜索，直接返回 True。
        }
        flags[i] = 1;  // 即标记其被本轮 DFS 访问过；
        for(Integer j : adjacency.get(i)){
            // 遍历 i 的每个邻接节点
            if (!dfs(adjacency, flags, j)){
                return false; // 当发现环直接返回False
            }
        }
        flags[i] = -1;  // 完成该轮遍历时，flag置为 -1
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
