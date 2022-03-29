/**
 * https://leetcode.com/problems/number-of-islands/
 *
 * DFS: Time O(mn), Space O(mn)
 * BFS: Time O(mn), Space O(m+n) = O(min(m,n))
 */
class Solution {

  public int numIslands(char[][] grid) {
    //corner case
    if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
      return -1;
    }

    //init
    int row = grid.length;
    int col = grid[0].length;
    int result = 0;
    boolean[][] visited = new boolean[row][col];

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == '1' && !visited[i][j]) {
          helper(grid, visited, row, col, i, j);
          result++;
        }
      }
    }

    return result;
  }

  private void helper(char[][] grid, boolean[][] visited, int row, int col, int i, int j) {
    int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    if (i < 0 || i >= row || j < 0 || j >= col || visited[i][j] || grid[i][j] != '1') {
      return;
    }

    visited[i][j] = true;
    for (int[] dir : DIRECTIONS) {
      helper(grid, visited, row, col, i + dir[0], j + dir[1]);
    }
  }
}