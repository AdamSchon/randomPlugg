-- The empty FamilyTree is given by void.
-- A non-empty with gender a, name b, birth year c, left subtree l and right
--subtree r is assigned through: a b c l r
--

data FamilyTree = Void
  | Man String Int FamilyTree FamilyTree
  | Woman String Int FamilyTree FamilyTree deriving(Show)


findOlder Nothing Nothing = Nothing
findOlder (String Int) Nothing = (String Int)
findOlder Nothing (String Int) = (String Int)
findOlder (String1 Int1) (String2 Int2)
  | Int1 > Int2 = (String1 Int2)
  | otherwise = (String2 Int2)

oldestWoman Void = Nothing
oldestWoman (Man name age left right) =
