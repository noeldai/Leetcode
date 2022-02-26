public class _98 {

  /**
   * Time: O(n) n: number of TreeNode - since we visited each node once
   * Space: O(n) hight of the tree at worst case is n instead of logn
   */
  class Solution {

    public boolean isValidBST(TreeNode root) {
      //corner case
      if (root == null) {
        return false;
      }

      return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean helper(TreeNode node, long lower, long upper) {
      if (node == null) {
        return true;
      }

      if (node.val >= upper || node.val <= lower) {
        return false;
      }

      return helper(node.left, lower, node.val) && helper(node.right, node.val, upper);
    }
  }
}