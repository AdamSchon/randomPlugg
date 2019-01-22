data Suite = Hearts | Diamonds | Clubs | Spades deriving(Show)

data Card = Hearts Int | Diamonds Int | Clubs Int | Spades Int deriving(Show)

greaterCard (Suite s1 r1) (Suite s2 r2) =
  if (r1 > r2)
    then True
    else False
