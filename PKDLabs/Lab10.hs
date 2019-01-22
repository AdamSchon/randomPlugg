data Card = Hearts Int | Diamonds Int | Clubs Int | Spades Int deriving(Show)

greaterCard (Card s1 r1) (Card s2 r2) =
  if (r1 > r2)
    then True
    else False
