class Solution {

  public int maxAreaOfIsland(int[][] grid) {
    int row = grid.length;
    int col = grid[0].length;
    boolean[][] visited = new boolean[row][col];
    int result = Integer.MIN_VALUE;

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == 1 && !visited[i][j]) {
          result = Math.max(result, helper(grid, visited, i, j));
        }
      }
    }

    return result == Integer.MIN_VALUE ? 0 : result;
  }

  private int helper(int[][] grid, boolean[][] visited, int i, int j) {
    int row = grid.length;
    int col = grid[0].length;
    if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == 0 || visited[i][j]) {
      return 0;
    }

    visited[i][j] = true;

    int[][] DIRECTION = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    int result = 1;
    for (int[] dir : DIRECTION) {
      result += helper(grid, visited, i + dir[0], j + dir[1]);
    }

    return result;
  }
}