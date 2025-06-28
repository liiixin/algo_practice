class Solution {
    // 这个是字节面试题：竹竿接水，和leetcode不一样
    // 有一批隔板，其高度用数组表示，相邻两隔板之间距离均为1，隔板本身不占体积，问下了足够多的雨之后，隔板中最多能蓄多少水？
    // 输入：[3,2,5,4,6,2]    输出：18
    public int trap(int[] height) {
        int sum = 0; // 初始化雨水总量为0
        int max_left = 0; // 初始化左边最高墙为0
        int max_right = 0; // 初始化右边最高墙为0
        int left = 0;
        int right = height.length - 1;
        while(left < right){
            // 从左往右更新
            if(height[left] < height[right]){ // 此时左边最高墙一定小于右边最高墙
                if(height[left] >= max_left){
                    max_left = height[left];  // 动态更新左边最高墙
                }
                sum += max_left; // 每次都可以储水

                left++;  // 这个代表 从左往右更新
                // 从右往左更新
            }else{
                if(height[right] >= max_right){
                    max_right = height[right];
                }
                sum += max_right;

                right--;  // 这个代表 从右往左更新
            }
        }
        return sum;
    }
}