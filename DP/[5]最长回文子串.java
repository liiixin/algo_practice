//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3532 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() < 2){
            return s;
        }
        int strLen = s.length();
        int maxStart = 0; // 最长回文串的起点
        int maxEnd = 0; // 最长回文串的终点
        int maxLen = 1; // 最长回文串的长度

        boolean[][] dp = new boolean[strLen][strLen];
        // 用一个 boolean dp[l][r] 表示字符串从 i 到 j 这段是否为回文。
        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])){
                    // 这里的 r - l <= 2 有两种情况，一是差为1，回文串长度为偶数；二是差为2，回文串长度为奇数
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen){ // 这里更新最长回文串的三个int值
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
