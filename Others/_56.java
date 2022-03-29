/**
 * https://leetcode.com/problems/merge-intervals/
 *
 * interval 扫描线 方法1: sort left endpoint
 * (s1, e1) (s2, e2)
 *  if s1 < s2:
 *      1. e1 < s2 不相交
 *      2. e1 >= s2 相交
 *
 * Time: O(nlogn)
 * Space: O(n)
 *
 * Follow up: How do you add intervals and merge them for a large stream of intervals? -> Cannot use sorting
 * https://leetcode.com/problems/merge-intervals/discuss/21452/Share-my-interval-tree-solution-no-sorting
 */
class Solution {

  public int[][] merge(int[][] intervals) {
    //corner case
    if (intervals == null || intervals.length == 0 || intervals[0] == null
        || intervals[0].length == 0) {
      return new int[0][];
    }

    //pre-processing
    Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]); //O(nlogn)

    //init
    List<int[]> result = new ArrayList<>();
    int[] prev = intervals[0];

    //main
    for (int[] interval : intervals) { //O(n)
      if (prev[1] >= interval[0]) { //overlap
        prev = new int[]{prev[0], Math.max(prev[1], interval[1])};
      } else { //non-overlap
        result.add(prev);
        prev = interval;
      }
    }

    //post-processing
    result.add(prev);

    return result.toArray(new int[result.size()][]);
  }
}