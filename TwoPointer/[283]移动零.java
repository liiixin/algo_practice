class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0){
            return;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                // 当前元素 !=0，就把其交换到左边，等于0的交换到右边
                // 这里交换改为了 赋值， 更简单了
                if (i > j){
                    nums[j] = nums[i]; // j记录的是下一个非零元素应该存放的位置
                    nums[i] = 0;   // 零元素则“被迫”移动到数组的右边。
                }
                j++;
            }
        }
    }
}