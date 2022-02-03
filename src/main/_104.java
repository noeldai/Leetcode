public class _104 {

  /**
   * Time: O(n)
   * Space: O(n)
   * Follow up: L550 Maximum Depth of N-ary Tree
   */
  class Solution {

    public int maxDepth(TreeNode root) {
      if (root == null) {
        return 0;
      }

      return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
  }
}