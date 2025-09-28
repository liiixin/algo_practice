//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 
//
// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。 
//
// 
//
// 
//
// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。 
//
// 
//
// 示例: 
//
// 输入: [2,1,5,6,2,3]
//输出: 10 
// Related Topics 栈 数组 
// 👍 1321 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 单调栈 经典例题
    // https://leetcode.cn/problems/largest-rectangle-in-histogram/solution/bao-li-jie-fa-zhan-by-liweiwei1419/
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0){
            return 0;
        }
        if (len == 1){
            return heights[0];
        }

        int res = 0;
        int[] newHeights = new int[len + 2];
        newHeights[0] = 0; // 左哨兵
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[len + 1] = 0; // 右哨兵
        len += 2;
        heights = newHeights;

        Deque<Integer> stack = new ArrayDeque<>(len);
        // 先放入左哨兵，这样在循环里就不用做非空判断，因为stack一定非空
        stack.addLast(0);

        for (int i = 1; i < len; i++) {
            while (heights[i] < heights[stack.peekLast()]){ // 当出现不再单调递增时，要让现在的栈顶元素出栈
                int curHeight = heights[stack.pollLast()];
                int curWidth = i - stack.peekLast() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.addLast(i); // 继续入栈
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
