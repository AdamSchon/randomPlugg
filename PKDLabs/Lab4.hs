  -- stringOfInteger :: Integer -> String

  stringOfInteger x =
    if ((abs x) > 9)
      then if (x > 0)
            then (stringOfInteger (div x 10)) ++ show(mod x 10)
            else "-" ++ (stringOfInteger (div (abs x) 10)) ++ show(10 - (mod x 10))
      else if (x > 0)
            then show(x)
            else if (not (x == 0))
                  then "-" ++ show(x)
                  else ""

recursiveSearch mainstring substring num =
if ((length substring) > (length (drop num mainstring)))
  then (-1)
  else if (substring == (drop num mainstring))
        then return num
        else recursiveSearch(mainstring substring num+1)

searchString mainstring substring = recursiveSearch mainstring substring 0
