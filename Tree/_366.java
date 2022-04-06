/**
 * https://leetcode.com/problems/find-leaves-of-binary-tree/
 *
 * 思路：
 * 我们需要对height进行post-processing，但是如何确定是leaves是难点
 * 我们可以巧妙的利用result的size来记录是第几层leaves
 *
 * Time: O(n)
 * Space: O(1)
 *
 *Followup:
 * 1. 必须逐层去掉叶节点
 * 2. we have given the Map =[2=[z,b] , 1=[e] ] with height = [leaf values] .
 * Construct the tree in such a way lower height leaf should come on the left hand side and if 2 leaves has same height ,
 * than alphabetically sorted value should come on left hand size . All other values ( non leaf values) , we can put *.
 */
class Solution {

  public List<List<Integer>> findLeaves(TreeNode root) {
    //corner case
    if (root == null) {
      return new ArrayList<>();
    }

    List<List<Integer>> result = new ArrayList<>();

    getHeight(result, root);

    return result;
  }

  private int getHeight(List<List<Integer>> result, TreeNode node) {
    if (node == null) {
      return -1; //算是一个corner case，这样curHeight返回上去才能等于0
    }

    int leftHeight = getHeight(result, node.left);
    int rightHeight = getHeight(result, node.right);
    int curHeight = 1 + Math.max(leftHeight, rightHeight);

    if (curHeight >= result.size()) {
      result.add(new ArrayList<>());
    }

    result.get(curHeight).add(node.val);

    return curHeight;
  }
}