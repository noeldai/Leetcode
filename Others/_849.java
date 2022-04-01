/**
 * https://leetcode.com/problems/maximize-distance-to-closest-person/
 *
 * 计算最大连续0的长度，头和尾特殊计算
 * 思路：连续0有三种情况 1）0001； 2）1000； 3）夹在两个1中间的0
 * 用一个prev来记录前一个1的位置
 *
 * Time: O(n)
 * Space:O(1)
 */
class Solution {

  public int maxDistToClosest(int[] seats) {
    int maxCount = Integer.MIN_VALUE;
    int prev = Integer.MIN_VALUE;

    for (int i = 0; i < seats.length; i++) {
      if (seats[i] == 1) {
        if (prev == Integer.MIN_VALUE) { //排除0001
          maxCount = i;
        } else { //计算1和1之间的0的maxCount
          maxCount = Math.max(maxCount, (i - prev) / 2);
        }

        prev = i;
      }
    }

    //排除1000
    return Math.max(maxCount, seats.length - prev - 1);
  }
}