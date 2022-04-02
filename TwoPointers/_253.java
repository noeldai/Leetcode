/**
 * https://leetcode.com/problems/meeting-rooms-ii/
 *
 * ----------------------------------------------
 * Method 1 - interval 扫描线方法2：建造一个EndPoint class
 * EndPoint - value (index, Start/End)
 * 看见Start的时候加房间数，看见End的时候减房间数
 *
 * example 1 regular case: [0, 30], [5, 10], [15, 20]
 *          -> 0 (0, L), 5 (1, L), 10 (1, R), 15 (2, L), 20 (2, R), 30 (0, R)
 * delta Room:  +1, +1, -1, +1, -1, -1
 * result Room: 1, 2, 1, 2, 1, 0
 *
 * example 2 tie case: [0, 30], [5, 10], [10, 20]
 * -> 0 (0, L), 5 (1, L), 10 (1, R), 10 (2, L), 20 (2, R), 30 (0,R)
 * 用例子来验证tie case，出现value一样的情况End要比Start先 -> 具体实现在compareTo里
 *
 * Time: O(nlogn)
 * Space: O(n)
 *
 * -----------------------------------------------
 * Method 2 - Priority Queues
 *
 * -----------------------------------------------
 * Follow up:
 * 1. 不返回一个需要几间room，而是返回这些room同时开会的那个时间段。如果有好几个时间段满足 返回最长的那个 -> 本质上是记录max room的时间段
 */
class Solution {

  public int minMeetingRooms(int[][] intervals) {
    if (intervals == null || intervals.length == 0 || intervals[0] == null
        || intervals[0].length == 0) {
      return 0;
    }

    List<EndPoint> list = new ArrayList<>();
    for (int[] itv : intervals) {
      list.add(new EndPoint(itv[0], false));
      list.add(new EndPoint(itv[1], true));
    }

    Collections.sort(list); //O(nlogn)
    int count = 0, max = 0;
    for (EndPoint cur : list) {
      if (!cur.isEnd) {
        count++;
      } else {
        count--;
      }

      max = Math.max(max, count);
    }

    return max;
  }

  class EndPoint implements Comparable<EndPoint> {

    public int val;
    public boolean isEnd;

    public EndPoint(int val, boolean isEnd) {
      this.val = val;
      this.isEnd = isEnd;
    }

    public int compareTo(EndPoint that) {
      return this.val != that.val
          ? this.val - that.val
          : (this.isEnd) ? -1 : 1;
    }
  }
}