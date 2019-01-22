data Suite = Hearts | Diamonds | Spades | Clubs deriving(Show)

data Card = Card Suite Int deriving(Show)

greaterCard (Card s1 r1) (Card s2 r2) =
  if (r1 > r2)
    then True
    else False
