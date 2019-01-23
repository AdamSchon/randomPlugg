data BSTree = Void | Node BSTree Int BSTree deriving (Show)

--findLargest (Node _ i Void) = i
--findLargest (Node _ _ right) = findLargest right

--delete (Node left i right) val =
--  if (i == val)
--  then i = findLargest(left)

deletemax (Node left i Void) = (i,left)
deletemax (Node left i right) = (z, Node left i tz)
  where (z,tz) = deletemax right
