class Solution {
    public String minWindow(String s, String t) {
        // 边界条件检查
        if (s == null || s.isEmpty() || t == null || t.isEmpty() || s.length() < t.length()) {
            return "";
        }

        // 使用数组记录字符频次，need记录目标字符串t中各字符的出现次数，have记录当前窗口中各字符的出现次数
        int[] need = new int[128];
        int[] have = new int[128];

        // 统计目标字符串t中每个字符的出现次数
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }

        // 初始化双指针和相关变量
        int left = 0, right = 0;           // 滑动窗口的左右边界
        int minLen = s.length() + 1;       // 最小窗口长度，初始设为不可能的值
        int count = 0;                     // 当前窗口中满足need条件的字符总数
        int start = 0;                     // 最小窗口的起始位置

        // 右指针遍历整个字符串s
        while (right < s.length()) {
            char rChar = s.charAt(right);

            // 如果当前字符不在目标字符串中，直接跳过
            if (need[rChar] == 0) {
                right++;
                continue;
            }

            // 当当前字符在窗口中的出现次数小于目标次数时，有效字符数增加
            if (have[rChar] < need[rChar]) {
                count++;
            }

            // 更新窗口中该字符的出现次数
            have[rChar]++;
            right++;

            // 当窗口满足条件时，尝试收缩左边界
            while (count == t.length()) {
                // 更新最小窗口信息
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }

                char lChar = s.charAt(left);

                // 如果左边界字符不在目标字符串中，直接移动左指针
                if (need[lChar] == 0) {
                    left++;
                    continue;
                }

                // 如果移除当前左边界字符会导致不满足条件，则减少count以退出内层循环
                if (have[lChar] == need[lChar]) {
                    count--;
                }

                // 减少窗口中该字符的计数
                have[lChar]--;
                left++;
            }
        }

        // 如果没有找到满足条件的子串，返回空字符串
        if (minLen == s.length() + 1) {
            return "";
        }

        // 返回最小覆盖子串
        return s.substring(start, start + minLen);
    }
}