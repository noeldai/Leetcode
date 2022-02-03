public class _106 {

  /**
   * inorder: left root right
   * postorder: left right root
   * root is the last position of postorder,
   * then we can get left subtree info from inorder, by locating the root position in inorder list
   *
   * Time: O(n)
   * Space: O(n)
   */
  class Solution {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
      //corner case
      if (inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0
          || inorder.length != postorder.length) {
        return null;
      }

      int len = inorder.length;
      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < len; i++) {
        map.put(inorder[i], i);
      }

      return helper(inorder, 0, len - 1, postorder, 0, len - 1, map);
    }

    private TreeNode helper(int[] inorder, int inStart, int inEnd, int[] postorder,
        int postStart, int postEnd, Map<Integer, Integer> map) {
      if (inStart > inEnd || inStart > inEnd) {
        return null;
      }

      int rootVal = postorder[postEnd];
      TreeNode root = new TreeNode(rootVal);
      int leftIndx = map.get(rootVal) - inStart;

      root.left = helper(inorder, inStart, inStart + leftIndx - 1,
          postorder, postStart, postStart + leftIndx - 1, map);
      root.right = helper(inorder, inStart + leftIndx + 1, inEnd,
          postorder, postStart + leftIndx, postEnd - 1, map);

      return root;
    }
  }
}