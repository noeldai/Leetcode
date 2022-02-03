/**
 * Key word: BST - 根节点的左子树都比根节点的值小，根节点的右子树都比根节点的值大
 * Think: 穷举所有的BST可能性 -> 如何穷举？ 明确根节点需要做什么
 * -> eg. n = 3：根节点分别是1，2，3的时候有几种可能性，结果就是所有可能性的加和
 * 优化：加memo？加，因为是连续存储
 * <p>
 * Time:
 * Space:
 * Follow up: L95: print out all possible solution
 */
public class _96 {

  class Solution {

    public int numTrees(int n) {
      //corner case
      if (n <= 0) {
        return -1;
      }

      if (n == 1) {
        return 1;
      }

      int[][] memo = new int[n + 1][n + 1];

      return helper(1, n, memo);
    }

    //计算闭区间[low, high] 有多少种bst可能性
    private int helper(int low, int high, int[][] memo) {
      //base case
      if (low > high) {
        return 1;
      }

      if (memo[low][high] != 0) {
        return memo[low][high];
      }

      int result = 0;
      for (int i = low; i <= high; i++) {
        int left = helper(low, i - 1, memo);
        int right = helper(i + 1, high, memo);
        result += left * right;
      }

      memo[low][high] = result;
      return result;
    }
  }
}