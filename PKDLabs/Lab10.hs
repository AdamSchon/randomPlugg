-- data Suite = Hearts | Diamonds | Spades | Clubs deriving(Show)

data Card = Card String Int deriving(Show)

greaterCard (Card s1 r1) (Card s2 r2) =
  if (r1 == r2)
    then if (s1 == s2)
      then False
      else if (s1 == "Spades" && not(s2 == "Spades"))
        then True
        else if (s1 == "Hearts" && (s2 == "Clubs" || s2 == "Diamonds"))
          then True
          else if (s1 == "Clubs" && s2 == "Diamonds")
            then True
            else False
    else if (r1 > r2)
          then True
          else False
