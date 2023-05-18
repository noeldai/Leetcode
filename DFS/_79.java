/**
 * Time: O(m*n*4^k)
 * 空间优化，可以mark visited -> board[i][j]='*';
 */
class Solution {

  public boolean exist(char[][] board, String word) {
    if (board == null || board.length == 0 || board[0] == null || board[0].length == 0
        || word == null || word.length() == 0) {
      return false;
    }

    int row = board.length;
    int col = board[0].length;
    for (int i = 0; i <= row; i++) {
      for (int j = 0; j <= col; j++) {
        if (helper(board, word, i, j, 0, new boolean[row][col])) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean helper(char[][] board, String word, int i, int j, int idx, boolean[][] visited) {
    int row = board.length;
    int col = board[0].length;
    // success case
    if (idx == word.length()) {
      return true;
    }

    // fail case
    if (i < 0 || i >= row || j < 0 || j >= col || board[i][j] != word.charAt(idx)
        || visited[i][j]) {
      return false;
    }

    //recursion body
    visited[i][j] = true;
    idx++;
    boolean result = helper(board, word, i + 1, j, idx, visited)
        || helper(board, word, i - 1, j, idx, visited)
        || helper(board, word, i, j + 1, idx, visited)
        || helper(board, word, i, j - 1, idx, visited);
    visited[i][j] = false;

    return result;
  }
}