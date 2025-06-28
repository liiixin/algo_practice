class Solution {
    public int trap(int[] height) {
        int sum = 0; // 初始化雨水总量为0
        int max_left = 0; // 初始化左边最高墙为0
        int max_right = 0; // 初始化右边最高墙为0
        int left = 0;
        int right = height.length - 1;
        while(left < right){
            // 从左向右更新
            if(height[left] < height[right]){ // 此时左边最高墙一定小于右边最高墙
                if(height[left] >= max_left){ // 要么不能储水且更新左边最高墙(if分支)， 要么能储水(else分支)
                    max_left = height[left];  // 当前这面墙不能储水，并且动态更新左边最高墙
                }else{
                    sum += max_left - height[left]; // 当前这面墙可以储水
                }
                left++;
            }else{
                if(height[right] >= max_right){
                    max_right = height[right];
                }else{
                    sum += max_right - height[right];
                }
                right--;
            }
        }
        return sum;
    }
}