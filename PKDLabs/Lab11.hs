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
  if (i1 > i2)
    then Just (s1,i1)
    else Just (s2,i2)

oldestWoman Void = Nothing

--oldestWoman Void = Nothing
--oldestWoman (Woman name age left right) =
--  (findOlder (Just (name, age)) (findOlder ((oldestWoman left) (oldestWoman right))))
--oldestWoman (Man name age left right) = findOlder ((oldestWoman left) (oldestWoman right))
