//冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。 
//
// 在加热器的加热半径范围内的每个房屋都可以获得供暖。 
//
// 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。 
//
// 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。 
//
// 
//
// 示例 1: 
//
// 
//输入: houses = [1,2,3], heaters = [2]
//输出: 1
//解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
// 
//
// 示例 2: 
//
// 
//输入: houses = [1,2,3,4], heaters = [1,4]
//输出: 1
//解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
// 
//
// 示例 3： 
//
// 
//输入：houses = [1,5], heaters = [2]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= houses.length, heaters.length <= 3 * 104 
// 1 <= houses[i], heaters[i] <= 109 
// 
// Related Topics 数组 二分查找 排序 
// 👍 218 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        //该法能成立的先决条件为两数组一定都要从小到大排好序
        Arrays.sort(heaters);
        Arrays.sort(houses);
        int l = 0 , r = Math.max(heaters[heaters.length - 1], houses[houses.length - 1]) - 1;
        //开始二分枚举
        while(l < r){
            int mid = (l+r) >>> 1;
            //成立时，右界移动至中心
            if(Helper(houses , heaters , mid)){
                r = mid;
                //不成立时，左界移至中心+1
                //这样可以保证最后的跳出循环l一定为半径最小
            }else{
                l = mid+1;
            }
        }
        return l;
    }
    public boolean Helper(int[] houses, int[] heaters, int len){
        int m = houses.length , n = heaters.length;
        int index = 0;
        for(int i = 0 ; i < n ; i++){
            long l = heaters[i]-len , r = heaters[i]+len;
            //计算能否完全覆盖房屋
            while(index < m && (long)houses[index] >= l && (long)houses[index] <= r){
                index++;
            }
            if(index == m) return true;
        }
        return false;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
