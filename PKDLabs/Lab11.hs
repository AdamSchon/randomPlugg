-- The empty FamilyTree is given by void.
-- A non-empty with gender a, name b, birth year c, left subtree l and right
--subtree r is assigned through: a b c l r
--

data FamilyTree = Void deriving(Show)
                | Man String Int FamilyTree FamilyTree deriving(Show)
                | Woman String Int FamilyTree FamilyTree deriving(Show)
