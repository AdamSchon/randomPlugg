data Suite = Hearts | Diamonds | Clubs | Spades deriving(Show)

data Rank = Rank Int

data Card = Card Suite Rank

greaterCard (Card s1 r1) (Card s2 r2) =
  if (r1 > r2)
    then True
    else False
