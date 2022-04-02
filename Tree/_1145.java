/**
 * https://leetcode.com/problems/binary-tree-coloring-game/
 *
 * 思路：
 * 1. x选定之后，y每当选定一个节点之后，在y背离x方向一层的节点就不再会被x染色到，也就是提示所说的，y选在哪里，就会堵住一条道路；
 * 2. 所以最好的方式就是，y贴着x选，这样能最早的堵住x发展下去的一条道路
 *    那么贴着x选最多有三个节点，x的左右孩子节点，x的父节点 （可以统计出x的左右孩子子树分别包含多少个节点，那么根据总节点数就可以得到x父节点往上的总节点数）
 * 3. 最后y选择节点数最多的一路来堵住x，如果这一路的节点数比x可以发展的另外两条路的节点数都多，那么y就找到了一个可以赢的位置
 *
 * Time: O(n)
 * Space: O(height) for recursion
 */
class Solution {

  public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
    //corner case
    if (root == null || n <= 1) {
      return false;
    }

    //init
    TreeNode xRoot = findNode(root, x);
    int leftSubtree = countNode(xRoot.left);
    int rightSubtree = countNode(xRoot.right);
    int halfNodeNum = n / 2;

    if (leftSubtree > halfNodeNum || rightSubtree > halfNodeNum
        || leftSubtree + rightSubtree < halfNodeNum) {
      return true;
    }

    return false;
  }

  private TreeNode findNode(TreeNode root, int x) {
    if (root == null) {
      return null;
    }

    if (root.val == x) {
      return root;
    }

    TreeNode left = findNode(root.left, x);
    TreeNode right = findNode(root.right, x);

    return left != null ? left : right;
  }

  private int countNode(TreeNode root) {
    if (root == null) {
      return 0;
    }

    return countNode(root.left) + countNode(root.right) + 1;
  }
}

//Method 2: 思路一样，只是写法上的优化，把 findNode和countNode结合了一下
class Solution {

  int left, right, val;

  public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
    val = x;
    count(root);

    return Math.max(Math.max(left, right), n - left - right - 1) > n / 2;
  }

  private int count(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int l = count(node.left), r = count(node.right);
    if (node.val == val) {
      left = l;
      right = r;
    }
    return l + r + 1;
  }
}