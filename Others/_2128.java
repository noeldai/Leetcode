/**
 * https://leetcode.com/problems/remove-all-ones-with-row-and-column-flips/
 *
 * Let's say there are some number of 1s in the first row. To get to all 0s matrix it is obvious that we must flip those 1s.
 * If we flip the first row itself then any 0 will turn into 1. So instead we flip all the columns that have 1 in the first row.
 * Now if we think a little deeply, we can realize that we can never flip columns again, because if we do, then 0s in first row will turn to 1s again.
 * So our only option is to flip rows now (starting from second row since first row is already all 0s).
 * Now to get all 0s in the final matrix each row ought to have either only 0s or only 1s.
 * If it has only 0s we don't flip it, if it has only 1s then we flip it.
 * If in any row all the elements are not same, it means it is not possible to get all 0s matrix.
 *
 * 我们先找到第一排row所有的1，把这些cell所在的col全部flip了。这个时候第一行全部都是0
 * 这时候我们不能对'col'进行任何的操作，否则会破坏第一排的all zero
 * 从第二排看起，每一排要么全是0或者全是1 才能满足我们的要求 （在代码中体现就是同一排，下一个数字一定和前一个数字相同）
 *
 * Time:O(mn)
 * Space:O(1)
 *
 * Followup: L2174 - minimum number of operations needed to remove all 1's from grid.
 */
class Solution {

  public boolean removeOnes(int[][] grid) {
    //corner case
    if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
      return false;
    }

    //init
    int rows = grid.length;
    int cols = grid[0].length;

    //main
    //flip the first row: if cell is 1, then flip current col as a whole
    for (int c = 0; c < cols; c++) {
      if (grid[0][c] == 1) {
        for (int i = 0; i < rows; i++) {
          grid[i][c] = (grid[i][c] == 0) ? 1 : 0;
        }
      }
    }

    //starting from second row, checking if the following rows have same value in each cell
    for (int r = 1; r < rows; r++) {
      for (int c = 0; c < cols - 1; c++) {
        if (grid[r][c] != grid[r][c + 1]) {
          return false;
        }
      }
    }

    return true;
  }
}