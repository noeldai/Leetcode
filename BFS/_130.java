/**
 * 搜索问题 - 寻找和边界不连接的'O'
 *
 * BFS: 把所有和边界联通的'O'加入queue里面，然后一层一层的找，那么最后我们没有触摸到的'O'就是需要flip的点
 * Time - O(m*n)
 * Space - O(m*n)
 *
 * DFS:
 */
class Solution {
  public void solve(char[][] board) {
    //corner case
    if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
      return;
    }

    int row = board.length;
    int col = board[0].length;
    Queue<Integer> queue = new LinkedList<>();

    //initialize - put all 'o' on board into queue
    addBoarderToQueue(board, queue, row, col);

    //implements
    int[][] Directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    while(!queue.isEmpty()) {
      int size = queue.size();
      while(size-- > 0) {
        int cur = queue.poll();
        int curI = cur/col, curJ = cur%col;

        for (int[] dir : Directions) {
          int nextI = curI+dir[0], nextJ = curJ+dir[1];
          if (nextI < 0 || nextI >= row || nextJ < 0 || nextJ >= col) {
            continue;
          }

          if (board[nextI][nextJ] == 'O') {
            queue.add(nextI*col+nextJ);
            board[nextI][nextJ] = 'A';
          }
        }
      }
    }

    //post-processing
    postProccessingToFlipBoard(board, row, col);
  }

  private void addBoarderToQueue (char[][] board, Queue<Integer> queue, int row, int col) {
    for (int i = 0; i < row; i++) {
      if (board[i][0] == 'O') {
        queue.add(i*col);
        board[i][0] = 'A';
      }

      if (board[i][col-1] == 'O') {
        queue.add(i*col+col-1);
        board[i][col-1] = 'A';
      }
    }

    for (int j = 1; j < col-1; j++) {
      if (board[0][j] == 'O') {
        queue.add(j);
        board[0][j] = 'A';
      }

      if (board[row-1][j] == 'O') {
        queue.add((row-1)*col+j);
        board[row-1][j] = 'A';
      }
    }
  }

  private void postProccessingToFlipBoard (char[][] board, int row, int col) {
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (board[i][j] == 'O') {
          board[i][j] = 'X';
        }

        if (board[i][j] == 'A') {
          board[i][j] = 'O';
        }
      }
    }
  }
}