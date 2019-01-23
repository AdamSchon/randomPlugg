-- The empty FamilyTree is given by void.
-- A non-empty with gender a, name b, birth year c, left subtree l and right
--subtree r is assigned through: a b c l r
--

data FamilyTree = Void
                | Man String Int FamilyTree FamilyTree
                | Woman String Int FamilyTree FamilyTree deriving(Show)


findOlder Nothing Nothing = Nothing
findOlder (s i) Nothing = (s i)
findOlder Nothing (s i) = (s i)
findOlder (s1 i1) (s2 i2) =
  if (i1 > i2)
    then (s1 i1)
    else (s2 i2)

oldestWoman Void = Nothing
oldestWoman (Man name age left right) =
