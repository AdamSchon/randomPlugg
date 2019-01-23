{- Tree datatype containing two children. An empty FamilyTree (or node) is given by Void.
  A non-empty with gender a, name b, birth year c, left subtree l and right
  subtree r is assigned through: a b c l r
-}
data FamilyTree = Void
  | Man String Int FamilyTree FamilyTree
  | Woman String Int FamilyTree FamilyTree deriving(Show)


{- findOlder (a1,a2) (b1,b2)
    Compares two tuples by comparing the values a2 and b2.
    RETURNS: The tuple with the lower value (a2 or b2). If they are equal (b1,b2) is returned.
    EXAMPLE:
    findOlder (Just ("Adam", 1992)) (Just ("Ola", 1993)) = Just ("Adam", 1992)
-}
findOlder Nothing Nothing = Nothing
findOlder Nothing (Just (s,i)) = Just (s,i)
findOlder (Just (s,i)) Nothing = Just (s,i)
findOlder (Just (s1, i1)) (Just(s2, i2)) =
  if (i1 < i2)
    then Just (s1,i1)
    else Just (s2,i2)


{-
  Function that searches for the oldest woman in a tree of type FamilyTree.
  RETURNS: Returns the oldest woman in the search tree in the form of 'Just ("name", age)'.
  If no woman is in the tree Nothing is returned
  EXAMPLE:
  oldestWoman (Woman "Anna" 1992 (Woman "Maria" 1989 Void Void) (Man "Arn" 1150 Void Void)) = Just ("Maria", 1989)
-}
oldestWoman Void = Nothing
oldestWoman (Woman name age left right) = findOlder (Just (name,age)) (findOlder (oldestWoman (left)) (oldestWoman (right)))
oldestWoman (Man _ _ left right) = (findOlder (oldestWoman (left)) (oldestWoman (right)))

-- Question 1 c)
-- Better to have it as an argument to ensure people who don't have a gender can be part of the database!
-- On a more serious I think it's faster to have seperate constructors for the two types.
-- As the check will take time to perform.


-- Question 2
-- Possibly the connection of rooms in a 2-D video game. One node containing the name of the
-- current room and up to four other possible rooms.

{-
  Tree datatype representing the rooms in a 2-D video game. The String is the name of the room and the following
  nodes represent (in order) it's north, south, east and west rooms. A non-existing room is represented by NoConnections.
-}
data RoomConnection = NoConnections
  | String RoomConnection RoomConnection RoomConnection RoomConnection
