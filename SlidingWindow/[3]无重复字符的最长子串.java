class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        // 滑动窗口
        int i = -1, res = 0;
        for (int j = 0; j < s.length(); j++) {
            if (dic.contains(s.charAt(j))) {
                i = Math.max(i, dic.get(s.charAt(j)));
                // 更新左指针，保证了左指针的单调递增，防止左指针回退。
            }
            dic.put(s.charAt(j), j);      // 记录每个字符最新出现的位置
            res = Math.max(res, j - i);   // 更新结果，为什么是 j - i，因为j是索引，i = -1或者重复的那个字符的索引，所以相减就是长度
        }
        return res;
    }
}