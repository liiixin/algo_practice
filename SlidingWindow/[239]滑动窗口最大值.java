class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 剑指offer 59
        // 窗口对应的数据结构为 双端队列 ，本题使用 单调队列 即可解决以上问题。遍历数组时，每轮保证单调队列 deque ：
        // deque内仅包含窗口内的元素 ⇒ 每轮窗口滑动移除了元素 nums[i - 1]，需将 deque内的对应元素一起删除。
        // deque内的元素 非严格递减 ⇒ 每轮窗口滑动添加了元素 nums[j + 1]，需将 deque内所有 < nums[j + 1] 的元素删除。

        if (nums.length == 0){
            return nums;
        }

        Deque<Integer> deque = new LinkedList<>();
        int[] arr = new int[nums.length - k + 1];
        int index = 0; // arr数组的下标
        // 未形成窗口区间
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] > deque.peekLast()){
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        // 窗口区间刚形成后，把队列首位值添加到队列中
        arr[index++] = deque.peekFirst();
        // 窗口区间形成
        for (int i = k; i < nums.length; i++) {
            // i-k表示上一个窗口的第一个元素，在当前区间没有，判断max值时，需要先删除
            if (deque.peekFirst() == nums[i - k]){
                deque.removeFirst();
            }
            // 删除队列中比当前值小的值
            while (!deque.isEmpty() && nums[i] > deque.peekLast()){
                deque.removeLast();
            }
            // 将当前值添加到队列中
            deque.addLast(nums[i]);
            // 把队列中的首位值添加到arr数组中
            arr[index++] = deque.peekFirst();
        }
        return arr;
    }
}