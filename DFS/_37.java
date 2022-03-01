class Solution {

  /**
   * Brute force：对于每个格子，（从上到下 从左到右）对于每个possible的数字填值
   * Time: O(9^(mn))
   * Space: O(mn)
   */
  public void solveSudoku(char[][] board) {
    //corner case
    if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
      return;
    }

    if (board.length != 9 || board[0].length != 9) {
      return;
    }

    dfs(board, 0, 0);
  }

  private boolean dfs(char[][] board, int i, int j) {
    //base case
    if (j == 9) {
      return dfs(board, i + 1, 0);
    }

    if (i == 9) {
      return true;
    }

    //main
    if (board[i][j] != '.') {
      return dfs(board, i, j + 1);
    }

    for (char temp = '1'; temp <= '9'; temp++) {
      if (isValid(board, temp, i, j)) {
        board[i][j] = temp;
        if (dfs(board, i, j + 1)) {
          return true;
        }
        board[i][j] = '.';
      }
    }

    return false;
  }

  private boolean isValid(char[][] board, char k, int i, int j) {
    for (int ii = 0; ii < 9; ii++) {
      if (board[ii][j] == k) {
        return false;
      }

      if (board[i][ii] == k) {
        return false;
      }

      if (board[(i / 3) * 3 + ii / 3][(j / 3) * 3 + ii % 3] == k) {
        return false;
      }
    }

    return true;
  }
}