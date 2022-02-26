public class _95 {

  class Solution {

    public List<TreeNode> generateTrees(int n) {
      //corner case
      if (n <= 0) {
        return new ArrayList<>();
      }

      return helper(1, n);
    }

    private List<TreeNode> helper(int low, int high) {
      List<TreeNode> result = new ArrayList<>();

      //base case
      if (low > high) {
        result.add(null);
        return result;
      }

      for (int i = low; i <= high; i++) {
        List<TreeNode> leftTree = helper(low, i - 1);
        List<TreeNode> rightTree = helper(i + 1, high);

        //构建以当前节点的所有左右子树
        for (TreeNode left : leftTree) {
          for (TreeNode right : rightTree) {
            TreeNode root = new TreeNode(i);
            root.left = left;
            root.right = right;
            result.add(root);
          }
        }
      }

      return result;
    }
  }
}