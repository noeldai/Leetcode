/**
 * https://leetcode.com/problems/valid-tic-tac-toe-state/
 *
 * Clarify:
 * 1. 一旦有一个玩家胜利那么游戏结束？
 * 2.board一定valid吗？如果有没下完但是有一方一定会在下一步棋胜利怎么办（eg: board = ["XOX","O O","XOX"]）？
 *
 * 分析游戏规则：
 * 1）第一个玩家总是'X'， 第二个玩家总是'O' -> 说明X的总数量一定大于等于O的总数量
 * 2）判断胜利条件是3个连续的行、列、对角线，因为先连成线就游戏结束所以不会有同时获胜 -> 判定胜利的条件
 * 3）获胜就立刻结束比赛。 -> 在检查胜利条件的基础上，通过棋子的个数来判断board是否合理
 *      如果第一个玩家获胜，因为第一个玩家是先手，所以X的数量比O多一个
 *      如果第二个玩家获胜，X的数量等于O的数量
 *
 * Edge case需要注意的是return用的是反逻辑（!false）而不是正逻辑(comment掉的代码)
 * 这样是为了帮助没有下完的情况,eg: board = ["XOX","O O","XOX"]
 *
 * Time：O (board area) = O(9) constant
 * Space: O(1)
 */
class Solution {

  public boolean validTicTacToe(String[] board) {
    int xCount = 0;
    int oCount = 0;

    for (String row : board) {
      for (char c : row.toCharArray()) {
        if (c == 'X') {
          xCount++;
        } else if (c == 'O') {
          oCount++;
        }
      }
    }

    return !((oCount != xCount && oCount != xCount - 1)
        || (oCount != xCount - 1 && win(board, 'X'))
        || (oCount != xCount && win(board, 'O')));
    // return (oCount == xCount || oCount == xCount - 1) &&
    //     ((oCount == xCount - 1 && win(board, 'X')) ||
    //     (oCount == xCount && win(board, 'O')));
  }

  public boolean win(String[] board, char p) {
    for (int i = 0; i < 3; i++) { //判断行和列
      if ((p == board[0].charAt(i) && p == board[1].charAt(i) && p == board[2].charAt(i))
          || (p == board[i].charAt(0) && p == board[i].charAt(1) && p == board[i].charAt(2))) {
        return true;
      }
    }

    //判断对角线
    return ((p == board[0].charAt(0) && p == board[1].charAt(1) && p == board[2].charAt(2))
        || (p == board[0].charAt(2) && p == board[1].charAt(1) && p == board[2].charAt(0)));
  }
}