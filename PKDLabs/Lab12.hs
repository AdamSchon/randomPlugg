data BSTree = Void | Node BSTree Int BSTree deriving (Show)

deletemax (Node t1 y Void) = (y,t1)
deletemax (Node t1 y t2) = (z, Node t1 y tz)
  where (z,tz) = deletemax t2

delete Void x = Void
delete (Node tleft y tright) x
  | x < y   = Node (delete tleft x) y tright
  | x > y   = Node tleft y (delete tright x)
delete (Node Void y tright) x   = tright
delete (Node tleft y tright) x = Node tz z tright
  where (z,tz) = deletemax tleft

-- delete (Node (Node (Node Void 0 Void) 1 (Node Void 2 Void)) 3 (Node (Node (Node Void 4 Void) 5 (Node Void 6 Void)) 7 (Node Void 8 Void))) 7
