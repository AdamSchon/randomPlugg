data BSTree = Void | Node BSTree Int BSTree deriving (Show)

--findLargest (Node _ i Void) = i
--findLargest (Node _ _ right) = findLargest right

delete (Node left i right) val =
  | i > val = (Node (delete left) i right)
  | i < val = (Node left i (delete right))
  | i == val = delete (Node left o right) val = Node tz z right
                  where (z,tz) = deletemax left

deleteMax (Node left i Void) = (i,left)
deleteMax (Node left i right) = (z, Node left i tz)
  where (z,tz) = deleteMax right

-- deleteMax (Node (Node (Node Void 0 Void) 1 (Node Void 2 Void)) 3 (Node (Node (Node Void 4 Void) 5 (Node Void 6 Void)) 7 (Node Void 8 Void)))
