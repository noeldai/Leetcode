class Solution {

  public List<List<Integer>> combinationSum3(int k, int n) {
    if (k > n) {
      return new ArrayList<>();
    }

    List<List<Integer>> result = new ArrayList<>();
    helper(result, new ArrayList<>(), k, n, 0, 0, 1);

    return result;
  }

  private void helper(List<List<Integer>> result, List<Integer> list, int k, int n, int curNum,
      int curSum, int idx) {
    if (curSum == n && curNum == k) {
      result.add(new ArrayList<>(list));
      return;
    }

    if (curSum >= n || curNum >= k) {
      return;
    }

    for (int i = idx; i <= 9; i++) {
      list.add(i);
      helper(result, list, k, n, curNum + 1, curSum + i, i + 1);
      list.remove(list.size() - 1);
    }
  }
}