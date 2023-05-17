class Solution {

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    // corner case
    if (candidates == null || candidates.length == 0 || target < 1 || target > 40) {
      return new ArrayList<>();
    }

    List<List<Integer>> result = new ArrayList<>();
    helper(result, new ArrayList<>(), candidates, target, 0, 0);

    return result;
  }

  private void helper(List<List<Integer>> result, List<Integer> list, int[] candidates, int target,
      int idx, int curSum) {
    int candidatesLength = candidates.length;

    //success case
    if (curSum == target && idx < candidatesLength) {
      result.add(new ArrayList<>(list));
      return;
    }

    //fail case
    if (curSum > target || idx >= candidatesLength) {
      return;
    }

    //recursion body
    for (int i = idx; i < candidatesLength; i++) {
      list.add(candidates[i]);
      helper(result, list, candidates, target, i, curSum + candidates[i]);
      list.remove(list.size() - 1);
    }
  }
}