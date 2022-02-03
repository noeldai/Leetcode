public class _105 {

  /**
   * preOrder[start] is the current root, find this root position in inOrder list, then the left
   * part to the inOrder root is the left subtree of current root -> we can use a HashMap to quickly
   * locate root position in inOrder list
   * little optimization: we can get rid of inorder[] and inEnd in helper
   * <p>
   * Time: O(n) - touch each node once
   * Space: O(n)
   */
  class Solution1 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
      //corner case
      if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) {
        return null;
      }

      if (preorder.length != inorder.length) {
        return null; //throw new IllegalArgumentException();
      }

      int len = inorder.length;
      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < len; i++) {
        map.put(inorder[i], i);
      }

      return helper(preorder, 0, len - 1, inorder, 0, len - 1, map);
    }

    private TreeNode helper(int[] preorder, int preStart, int preEnd, int[] inorder,
        int inStart, int inEnd, Map<Integer, Integer> map) {
      //base case
      if (preStart > preEnd || inStart > inEnd) {
        return null;
      }

      int curRootVal = preorder[preStart];
      TreeNode root = new TreeNode(curRootVal);
      int leftIndex = map.get(curRootVal) - inStart; //扣除inorder的起始位数

      root.left = helper(preorder, preStart + 1, preStart + leftIndex,
          inorder, inStart, inStart + leftIndex - 1, map); //start和end的位置是闭区间
      root.right = helper(preorder, preStart + leftIndex + 1, preEnd,
          inorder, inStart + leftIndex + 1, inEnd, map);

      return root;
    }
  }
}