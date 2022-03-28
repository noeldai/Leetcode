/**
 * https://leetcode.com/problems/robot-room-cleaner/
 * Go forward, cleaning and marking all the cells on the way as visited.
 * At the obstacle turn right, again go forward, etc. Always turn right at the obstacles and then go forward.
 *
 * Time: O(n) - n: number of non-obstacles cells
 * Space: O(n)
 *
 * Followup: 房间大的时候程序哪里会最先出问题，怎么解决?
 */
class Solution {

  int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //forward, right, down, left
  Set<Pair<Integer, Integer>> visited = new HashSet<>();
  Robot robot;

  public void cleanRoom(Robot robot) {
    //成功：所有的格子都扫过 -> 如何确定所有的格子？
    //失败：碰到墙

    //分叉：1. 直走 2. 左转 3. 右转
    this.robot = robot;
    helper(0, 0, 0);
  }

  private void helper(int row, int col, int dir) {
    visited.add(new Pair(row, col));
    robot.clean();

    for (int i = 0; i < 4; i++) {
      int newDir = (dir + i) % 4;

      int newDirRow = row + DIRECTIONS[newDir][0];
      int newDirCol = col + DIRECTIONS[newDir][1];

      if (!visited.contains(new Pair(newDirRow, newDirCol)) && robot.move()) {
        helper(newDirRow, newDirCol, newDir);
        goBack();
      }

      robot.turnRight();
    }
  }

  private void goBack() {
    robot.turnRight();
    robot.turnRight();
    robot.move();
    robot.turnRight();
    robot.turnRight();
  }
}