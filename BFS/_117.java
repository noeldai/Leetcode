class Solution {
  public Node connect(Node root) {
    //corner case
    if (root == null) {
      return null;
    }

    if (root.left == null && root.right == null) {
      return root;
    }

    Queue<Node> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      Node prev = null;
      while (size-- > 0) {
        Node cur = queue.poll();
        if (cur.left != null) {
          queue.add(cur.left);
        }
        if (cur.right != null) {
          queue.add(cur.right);
        }

        if (prev != null) {
          prev.next = cur;
        }
        prev = cur;
      }
    }

    return root;
  }
}