#Tree
Thinking:
Tree的题目dfs base case应该是null还是leaf？

##Traversals
```
         1
     2        3
 4       5
```
1. in order (left, root, right): 4 2 5 1 3
2. pre order (root, left, right): 1 2 4 5 3
3. post order (left, right, root): 4 5 2 3 1

binary search tree: 根节点的左子树都比根节点的值小，根节点的右子树都比根节点的值大
The left subtree of a node contains only nodes with keys lesser than the node’s key.
The right subtree of a node contains only nodes with keys greater than the node’s key.
The left and right subtree each must also be a binary search tree.