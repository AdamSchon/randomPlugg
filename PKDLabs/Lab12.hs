data BSTree = Void | Node BSTree Int BSTree deriving (Show)

--findLargest (Node _ i Void) = i
--findLargest (Node _ _ right) = findLargest right

--deleteMax (Node left i Void) = (i,left)
--deleteMax (Node left i right) = (z, Node left i tz)
--  where (z,tz) = deleteMax right

--delete (Node left i right) val
--  | i > val = (Node (delete left) i right)
--  | i < val = (Node left i (delete right))
--delete (Node Void i right) val = right
--delete (Node left o right) val = (Node tz z right)
--      where (z,tz) = deleteMax left

--deletemax :: (BSTree a) -> (a,BSTree a)
deletemax (Node t1 y Void) = (y,t1)
deletemax (Node t1 y t2) = (z, Node t1 y tz)
  where (z,tz) = deletemax t2

--deletetree :: (BSTree a) -> a -> (BSTree a)
deletetree Void x = Void
deletetree (Node tleft y tright) x
  | x < y   = Node (deletetree tleft x) y tright
  | x > y   = Node tleft y (deletetree tright x)
deletetree (Node Void y tright) x   = tright
deletetree (Node tleft y tright) x = Node tz z tright
  where (z,tz) = deletemax tleft



-- deleteMax (Node (Node (Node Void 0 Void) 1 (Node Void 2 Void)) 3 (Node (Node (Node Void 4 Void) 5 (Node Void 6 Void)) 7 (Node Void 8 Void)))
