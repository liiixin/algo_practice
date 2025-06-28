class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        for(int i = 0, j = height.length - 1; i < j; ){
            int minHight = height[i] < height[j] ? height[i++] : height[j--];
            int area = (j - i + 1) * minHight;
            max = Math.max(max, area);
        }
        return max;
    }
}