/**
 * https://leetcode.com/problems/meeting-rooms/
 *
 * 可以直接利用判断interval的特性来做
 *
 * 方法1: sort left endpoint
 *   (s1, e1) (s2, e2)
 *   if s1 < s2:
 *       1. e1 < s2 不相交
 *       2. e1 >= s2 相交 -> 返回false
 *
 * Time: O(nlogn) caused by sorting
 * Space: O(1)
 */
class Solution {

  public boolean canAttendMeetings(int[][] intervals) {
    //corner case
    if (intervals == null || intervals.length == 0 || intervals[0] == null
        || intervals[0].length == 0) {
      return true;
    }

    if (intervals.length == 1) {
      return true;
    }

    //pre-processing
    Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);

    //main
    for (int i = 0; i < intervals.length - 1; i++) {
      if (intervals[i][1] > intervals[i + 1][0]) { //if s1 < s2, e1 > s2 相交
        return false;
      }
    }

    return true;
  }
}