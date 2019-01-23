-- The empty FamilyTree is given by void.
-- A non-empty with gender a, name b, birth year c, left subtree l and right
--subtree r is assigned through: a b c l r
--

data FamilyTree = Void
  | Man String Int FamilyTree FamilyTree
  | Woman String Int FamilyTree FamilyTree deriving(Show)

data Person = Nobody
  | String Int deriving(Show)

findOlder :: Person -> Person -> Person
findOlder Nobody Nobody = Nothing
findOlder s i Nobody = s i
findOlder Nobody s i = s i
findOlder s1 i1 s2 i2
  | i1 > i2 = (s1 i1)
  | otherwise = (s2 i2)

oldestWoman Void = Nothing
oldestWoman (Woman name age left right) =
  maybe Nothing findOlder (name age) (maybe Nothing findOlder ((oldestWoman left) (oldestWoman right)))
oldestWoman (Man name age left right) = maybe Nothing findOlder ((oldestWoman left) (oldestWoman right))
