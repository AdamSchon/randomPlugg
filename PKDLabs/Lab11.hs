-- The empty FamilyTree is given by void.
-- A non-empty with gender a, name b, birth year c, left subtree l and right
--subtree r is assigned through: a b c l r
--

data FamilyTree = Void
  | Man String Int FamilyTree FamilyTree
  | Woman String Int FamilyTree FamilyTree deriving(Show)


--findOlder :: Person -> Person -> Person
findOlder Nothing Nothing = Nothing
findOlder Nothing (Just (s,i)) = Just (s,i)
findOlder (Just (s,i)) Nothing = Just (s,i)
findOlder (Just (s1, i1)) (Just(s2, i2)) =
  if (i1 < i2)
    then Just (s1,i1)
    else Just (s2,i2)

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

data roomConnection = Void 
  | String roomConnection roomConnection roomConnection roomConnection
