class Solution {

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    if (candidates == null || candidates.length == 0 || target < 0) {
      return new ArrayList<>();
    }

    List<List<Integer>> result = new ArrayList<>();

    Arrays.sort(candidates);
    dfs(result, new ArrayList<>(), candidates, target, 0, 0);

    return result;
  }

  private void dfs(List<List<Integer>> result, List<Integer> list, int[] candidates, int target,
      int idx, int curSum) {
    if (curSum == target) {
      result.add(new ArrayList<>(list));
      return;
    }

    if (idx >= candidates.length || curSum >= target) {
      return;
    }

    for (int i = idx; i < candidates.length; i++) {
      list.add(candidates[i]);
      dfs(result, list, candidates, target, i + 1, curSum + candidates[i]);
      list.remove(list.size() - 1);

      while (i + 1 < candidates.length && candidates[i + 1] == candidates[i]) {
        i++;
      }
    }
  }
}