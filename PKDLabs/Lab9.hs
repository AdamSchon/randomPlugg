sizeList list = map length list

multiples n list = filter ((0==) . (\x -> mod x n)) list

myConcat = foldr (++) ""

-- The function multiplies all the numbers in the list and returns the result.

data Suite = Suite Hearts | Diamonds | Clubs | Spades deriving(Show)

data Rank = Rank Int

data Card = Card Suite Rank

greaterCard (Card s1 r1) (Card s2 r2) =
  if (r1 > r2)
    then true
    else false
