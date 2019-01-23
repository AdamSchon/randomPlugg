{-
  A binary search tree where a nodes value is an Int. Each node has two children, the left one is for lower
  values and the right one for higher values. An empty node is represented as Void.
-}
data BSTree = Void | Node BSTree Int BSTree deriving (Show)

{-
  Function that searches a tree (following the right side) for the highest value. When the value is found
  it is returned together with the tree, except that the node with the largest value is deleted.
  RETURNED: A tuple containing the largest value and the tree without the node with the deleted value.
-}
deletemax (Node t1 y Void) = (y,t1)
deletemax (Node t1 y t2) = (z, Node t1 y tz)
  where (z,tz) = deletemax t2

{-
  Function that searches a binary search tree for a node with value x. If a node is found it is deleted from the tree.
  If no node is found the
  RETURNS: Returns the entire tree without the node with the value x. If no node with value x is found, the original tree is returned.
-}
delete Void x = Void
delete (Node tleft y tright) x
  | x < y   = Node (delete tleft x) y tright
  | x > y   = Node tleft y (delete tright x)
delete (Node Void y tright) x   = tright
delete (Node tleft y tright) x = Node tz z tright
  where (z,tz) = deletemax tleft

-- delete (Node (Node (Node Void 0 Void) 1 (Node Void 2 Void)) 3 (Node (Node (Node Void 4 Void) 5 (Node Void 6 Void)) 7 (Node Void 8 Void))) 7
