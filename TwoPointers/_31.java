/**
 * https://leetcode.com/problems/next-permutation/
 *
 * 题干的意思是：找出这个数组排序出的所有数中，刚好比当前数大的那个数
 * 比如当前 nums = [1,2,3]。这个数是123，找出1，2，3这3个数字排序可能的所有数，排序后，比123大的那个数 也就是132
 * 如果当前 nums = [3,2,1]。这就是1，2，3所有排序中最大的那个数，那么就返回1，2，3排序后所有数中最小的那个，也就是1，2，3 -> [1,2,3]
 *
 * 思路：
 * 1. 我们需要找到靠后的'大数'和相对靠前的'小数'交换，比如1234中将4和3交换
 * 2. 同时我们希望下一个数字增加的幅度尽可能的小
 *      2.1 尽可能靠右的位置进行交换 -> 从右往左扫
 *      2.2 找到可能小的大数 -> 难点是怎么找！见下面
 *      2.3 交换大小数之后,再把大数后面的数字sort成升序
 *
 * 怎么找大数，小数：
 * 1. 从后向前查找第一个相邻升序的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
 * 2. 在 [j,end) 从后向前查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」、「大数」
 * 3. 将 A[i] 与 A[k] 交换
 * 4. 可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
 * 5. 如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {

  public void nextPermutation(int[] nums) {
    int i = nums.length - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
      i--;
    }
    if (i >= 0) {
      int j = nums.length - 1;
      while (j >= 0 && nums[i] >= nums[j]) {
        j--;
      }
      swap(nums, i, j);
    }
    reverse(nums, i + 1);
  }

  public void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public void reverse(int[] nums, int start) {
    int left = start, right = nums.length - 1;
    while (left < right) {
      swap(nums, left, right);
      left++;
      right--;
    }
  }
}