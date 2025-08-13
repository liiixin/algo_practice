class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        // 记录 p 的所有字母及其个数
        int[] need = new int[26];
        for (int i = 0; i < p.length(); i++) {
            need[p.charAt(i) - 'a']++;
        }
        // left 和 right 控制滑动窗口的大小
        int left = 0, right = 0;
        int[] window = new int[26];
        List<Integer> res = new ArrayList<Integer>();
        while (right < s.length()){
            window[s.charAt(right) - 'a']++;  // 加入窗口数据，并记录个数
            if (right - left + 1 == p.length()){
                // 维护一个长度为 p.length() 的窗口，并更新答案
                if (Arrays.equals(window, need)){
                    res.add(left);
                }
                // 删掉当前左指针指向的数据
                window[s.charAt(left) - 'a']--;
                // 然后左指针右移
                left++;
            }
            // 右指针右移
            right++;
        }
        return res;
    }
}