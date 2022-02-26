public class _108 {

  /**
   * Preorder traversal: always choose left middle node as root Time: O(n) Space: O(logn)
   */
  class Solution1 {

    public TreeNode sortedArrayToBST(int[] nums) {
      if (nums == null || nums.length == 0) {
        return null;
      }

      return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int start, int end) {
      if (start > end) {
        return null;
      }

      int midPoint = start + (end - start) / 2;
      //if we want to have middle right solution
      if ((start + end) % 2 == 1) {
        midPoint++; //if we want to random left and right: p += rand.nextInt(2);
      }

      TreeNode root = new TreeNode(nums[midPoint]);
      root.left = helper(nums, start, midPoint - 1);
      root.right = helper(nums, midPoint + 1, end);

      return root;
    }
  }
}