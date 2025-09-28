//给定一个二叉树，检查它是否是镜像对称的。 
//
// 
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
// 
//
// 
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
// 
//
// 
//
// 进阶： 
//
// 你可以运用递归和迭代两种方法解决这个问题吗？ 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 1414 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return root == null ? true : isSymmetric(root.left, root.right);
    }
    private boolean isSymmetric(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null) return true; // 当左右同时越过叶节点：此树从顶至底都对称，因此返回true
        if(root1 == null || root2 == null) return false; // 当左或右中只有一个越过叶节点：此树不对称，返回false
        if(root1.val != root2.val) return false; // 当左节点 != 右节点：此树不对称，返回false
        // 递推工作：通过比较二叉树的前序遍历序列和对称前序遍历序列来判断
        return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
